document.addEventListener('DOMContentLoaded', function () {
    var urlParams = new URLSearchParams(window.location.search);
    var width = urlParams.get('width');
    var height = urlParams.get('height');
    var bbox = urlParams.get('bbox');

    function getCenterAndZoom(width, height, bbox) {

        const bboxArray = bbox.split(',').map(Number);

        if (bboxArray.length !== 4) {
              return { center: [0, 0], zoom: 1 };
        }

        const center = [
            (bboxArray[0] + bboxArray[2]) / 2,
            (bboxArray[1] + bboxArray[3]) / 2
        ];

        const bboxWidth = bboxArray[2] - bboxArray[0];
        const bboxHeight = bboxArray[3] - bboxArray[1];
        const zoom = Math.min(
            Math.log(width / bboxWidth * 100000) / Math.log(2),
            Math.log(height / bboxHeight * 100000) / Math.log(2)
        );

        return { center, zoom };
    }


    const { center, zoom } = getCenterAndZoom(width, height, bbox);

    var map = new ol.Map({
        target: 'map',
        layers: [
            new ol.layer.Tile({
                source: new ol.source.OSM()
            })
        ],
        view: new ol.View({
            center: center,
            zoom: zoom
        })
    });

     const mapContainer = document.getElementById('map');
     mapContainer.style.width = `${width}px`;
     mapContainer.style.height = `${height}px`;

    var apiUrl = 'http://gateway-service:4000/map-rendering/objects?width=' + width + '&height=' + height;

    if (bbox !== null) {
        apiUrl += '&bbox=' + bbox;
    }
    fetch(apiUrl,{
        method: 'GET',
            headers: {
                'Accept': 'application/json',
            }
    })
        .then(response => {
             if (!response.ok) {
                return response.json().then(errorResponse => {
                    throw new Error(`Status: ${response.status}, Error: ${errorResponse.message}`);
                });
             }
             return response.json();
        })
        .then(serverResponse => {
            const format = new ol.format.WKT();
            const features = [];
            for (const ope of serverResponse) {
                const geometry = format.readGeometry(ope.geometry);

                const style = new ol.style.Style({
                    image: geometry instanceof ol.geom.Point ? new ol.style.Circle({
                        radius: 5,
                        fill: new ol.style.Fill({
                            color: ope.colorCharacteristic
                            }),
                        stroke: new ol.style.Stroke({
                            color: ope.colorCharacteristic,
                            width: 2
                            })
                        }) : undefined,
                        stroke: geometry instanceof ol.geom.Point ? undefined : new ol.style.Stroke({
                            color: ope.colorCharacteristic,
                            width: 2
                        })
                    });

                const feature = new ol.Feature({
                    geometry: geometry,
                    id: ope.id,
                    colorCharacteristic: ope.colorCharacteristic
                    });

                feature.setStyle(style);
                features.push(feature);
            }
            map.addLayer(
                new ol.layer.Vector({
                    source: new ol.source.Vector({
                        features: features
                    })
                })
            );
        })
        .catch(error => {
                mapContainer.style.display = 'none';
                document.getElementById('error-message').innerText = 'Error: ' + error.message;
            });
});
