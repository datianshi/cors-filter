var pet = {
    model : {},
};

//var host = "http://cors-res.techdora.cloudfoundry.me";
var host = "http://localhost:8080/cors-res";

pet.model.Pet = Backbone.Model.extend({
	
    urlRoot : host + '/pet',
    
    url: function() {
    	url = this.urlRoot;
        if (this.id)
        {
            url = this.urlRoot+ '/' + this.id;
        }
        return url;
    }
});