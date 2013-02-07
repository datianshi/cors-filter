//host = "http://cors-res.techdora.cloudfoundry.me";
host = "http://localhost:8080/cors-res";

Filter = Backbone.Model.extend({
	
    urlRoot : host + '/pet/config',
    
    url: function() {
    	url = this.urlRoot;
        return url;
    }
});


$(function() {
	
	FilterView = Backbone.View.extend({
		el : '#filter',
		
		events : {
			'click #update' : 'update',
			'click #get' : 'get'
		},
		
		initialize : function() {
			_.bindAll(this, 'update','get');
		},
		
		update : function() {
			var headers =  $('#supportedHeaders').val();
			var supportedMethods = $('#supportedMethods').val();
			var allowOrigin = $('#allowOrigin').val();
			var allowSubDomain = $('#allowSubdomains').val();
				
			filterModel = new Filter();
			filterModel.set({'cors.supportedHeaders':headers, 'cors.supportedMethods' : supportedMethods , 'cors.allowOrigin' : allowOrigin, 'cors.allowSubdomains' : allowSubDomain});
			filterModel.save(null,
					{       
							success : function(model){
								alert("Model updated");
							},
							error : function(model, xhr, options){
								console.log(xhr);
								alert("Can not post to remote server with updated filter config");
							}
							
					}
					);
		},
		
		get : function(){
			filterModel = new Filter();
			filterModel.fetch({
				success : _.bind(function(model){
					this.render(model);
				}, this),
				error: function(){
					alert("Can not retrieve filter config from remote server");
					}
				}
			);
		},
		
		render : function(model){
			$('#supportedHeaders').val(model.get('cors.supportedHeaders'));
			$('#supportedMethods').val(model.get('cors.supportedMethods'));
			$('#allowOrigin').val(model.get('cors.allowOrigin'));
			$('#allowSubdomains').val(model.get('cors.allowSubdomains'));
		}
		
	});
	
	var filterView = new FilterView();
	filterView.get();
});