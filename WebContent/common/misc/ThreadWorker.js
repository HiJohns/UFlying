function http(data) {
	var xhr;

	if(typeof XMLHttpRequest !== 'undefined') xhr = new XMLHttpRequest();
	else {
		var versions = ["MSXML2.XmlHttp.5.0", 
			 	"MSXML2.XmlHttp.4.0",
			 	"MSXML2.XmlHttp.3.0", 
			 	"MSXML2.XmlHttp.2.0",
			 	"Microsoft.XmlHttp"]

		for(var i = 0, len = versions.length; i < len; i++) {
		try {
			xhr = new ActiveXObject(versions[i]);
			break;
		}
			catch(e){}
		} // end for
	}
		
	xhr.onreadystatechange = ensureReadiness;
		
	function ensureReadiness() {
		if(xhr.readyState !== 4 || finished) {
			return;
		}

		// all is well	
		finished = true;
		if (xhr.status === 200) {
			self.postMessage({
				id: data.id,
				success: true, 
				result: xhr.responseText
			});
		}
		else {
			self.postMessage({
				id: data.id,
				success: false,
				result: xhr.status
			})
		}
	}	
	
	var finished = false;

	xhr.open(data.method || 'GET', data.url, true);
	xhr.send(data.data);
	
	setTimeout(function () {
		if (finished) return;
		finished = true;
		self.postMessage({
			id: data.id,
			success: false,
			result: 'timeout'
		});
	}, data.wait || 10000);
}

function messageHandler(e) {
	new http(e.data);
}
	
var self = this;

this.addEventListener('message', messageHandler, false);