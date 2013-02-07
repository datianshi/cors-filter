var pet = {
    model : {},
};

pet.model.Pet = Backbone.Model.extend({
	
    urlRoot : "http://cors-res.techdora.cloudfoundry.me/pet",
    
    url: function() {
    	url = this.urlRoot;
        if (this.id)
        {
            url = this.urlRoot+ '/' + this.id;
        }
        return url;
    }
});