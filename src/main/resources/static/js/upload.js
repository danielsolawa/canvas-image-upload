/**
 * 
 */

function snap(screen){
	var appCtx = screen.value;
	var video = document.getElementById("video");
	
	
	start(appCtx, video);
}

function start(appCtx, video){
	
	var output = document.getElementById("output");
	var canvas = drawCanvas(video);
	
	var url = canvas.toDataURL('image/png');
	
	output.innerHtml ='';
	output.appendChild(canvas);
	
	upload(appCtx, url);
}

function drawCanvas(video){
	var height = video.height;
	var width = video.width;
	
	var canvas = document.createElement("canvas");
	canvas.height = height;
	canvas.width = width;
	
	var ctx = canvas.getContext("2d");
	ctx.drawImage(video, 0, 0 , width, height);
	
	return canvas;
}

function successScreenshot(data) {
 console.log(data.name);
}

function errorScreenshot(data){
	
}

function upload(appCtx, url) {
	

	$.ajax({
		"type" : "POST",
		"url" : appCtx,
		"data" : JSON.stringify({
			"imgBase64" : url
		}),
		"success" : successScreenshot, 
		"error" : errorScreenshot,
		contentType : "application/json",
		dataType : "json"
	});

}