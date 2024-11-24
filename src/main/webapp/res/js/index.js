
function getLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition);
	}
}

function showPosition(position) {
	console.log(position.coords.latitude);
	console.log(position.coords.longitude);

	document.getElementById('inputLat').value = position.coords.latitude;
	document.getElementById('inputLnt').value = position.coords.longitude;
}

window.addEventListener('load', function (event) {

	document.getElementById('getPositionButton').addEventListener('click', function (event) {
		getLocation();
	});

});