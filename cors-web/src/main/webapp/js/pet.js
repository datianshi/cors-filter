$(function() {

	PetView = Backbone.View.extend({
		el : '#pet',
		
		events : {
			'click #create' : 'create',
			'click #update' : 'update',
			'click #delete' : 'remove',
			'click #get' : 'get',
			'click #getAll' : 'getAll',
		},

		initialize : function() {
			_.bindAll(this, 'create', 'update', 'remove','get');
			this.petModel = new pet.model.Pet();
		},

		create : function() {
			this.clearField();
			var name = $('#name').val();
			var category = $('#category').val();
			petModel = this.initializeModel();
			petModel.set({name : name , category : category});
			petModel.save(null,
					{       
							success : function(model){
								model.set({operation : "save"});
							},
							error : function(model, response, error){
								alert("error");
							}
							
					}
					);
		},
		
		update : function() {
			this.clearField();
			var id =  $('#id').val();
			var name = $('#name').val();
			var category = $('#category').val();
			petModel = this.initializeModel();
			petModel.set({id:id, name : name , category : category});
			petModel.save(null,
					{       
							success : function(model){
								model.set({operation : "update"});
							},
							error : function(error){
								alert("error");
							}
							
					}
					);
		},
		
		remove : function() {
			this.clearField();
			var id =  $('#id').val();
			petModel = this.initializeModel();
			petModel.set({id:id});
			petModel.destroy(
					{       
							success : function(model){
								model.set({operation : "delete"});
							},
							error : function(error){
								alert("error");
							}
							
					}
					);
		},
		
		get : function() {
			this.clearField();
			var id =  $('#id').val();
			petModel = this.initializeModel();
			petModel.set({id : id});
			petModel.fetch(
					{       
							success : function(model, response)
							{	
								if (response == null) {
									alert("The object with id:" + id + " does not exists");
								}
								else{
									model.set({operation : 'get'});
								}
							},
							error : function(error){
								alert("error");
							}
							
					}
					);
		},
		
		getAll : function() {
			this.clearField();
			var pets = new Backbone.Collection;
			pets.url= host + '/pet';
			pets.fetch(
					{
						success : _.bind(function(pets){
							pets.each(_.bind(function(model){
							this.$el.find('#response').append('<div class="hero-unit"><p>id:' + model.get('id') + '</p><p>name:'+model.get('name')+'</p><p>category:'+model.get('category')+'</p></div>');
						  },this)
						);
						},this),
						error : function(){alert('error');}
					});
			
		},		

		render : function(model) {
			var op = model.get("operation");
			var title = $('#info').text();
			switch(op)
			{
				case 'save' :
					title = "The pet is saved id is:" + model.get('id');
					this.setField(model);
					break;
				case 'update' :
					title = "The pet with id:" + model.get('id') + " updated to name, category " + model.get('name') + "," + model.get('category');
					this.setField(model);
					break;
				case 'get' :
					this.$el.find('#response').append('<div class="hero-unit"><p>id:' + model.get('id') + '</p><p>name:'+model.get('name')+'</p><p>category:'+model.get('category')+'</p></div>');
					break;
				case 'delete' :
					title = 'The pet with id:' + model.get('id') + ' deleted';
					break;
				default:
					break;
			}
			this.$el.find('#info').text(title);
			return this;
		},
		
		clearField: function() {
			this.$el.find('#response').empty();
		},
		
		setField: function(model) {
			this.$el.find('#id').val(model.get('id'));
			this.$el.find('#name').val(model.get('name'));
			this.$el.find('#category').val(model.get('category'));
		},
		
		initializeModel : function(model) {
			var thisView = this;
			petModel = new pet.model.Pet();
			petModel.bind("change", function(model){
				thisView.render(model);
			});
			return petModel;
		}

	});
	

	var petView = new PetView();
});
