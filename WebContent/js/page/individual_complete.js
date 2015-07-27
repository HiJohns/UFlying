UF.page.IndividualComplete = (function () {
	return {
		dependencies: [
		               'Form', 
		               'Mobile',
		               'Complete'
		               ],
		init: function () {
		    // hack
		    model.mobilePhone = model.phone;
		    model.emailAddress = model.email;
		    model.headPhotoUrl = model.photoUrl;
		}
	}
})();
