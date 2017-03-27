$(document).ready(function() {
    
	
	var tree_well= $('tree');

	input(1);
   

});

function input(num) {
    for (var i = 0; i < num; i++) {
        $('.tree').add( "ul").add(function () {
        	for(var j=0;j<5;j++){
        		$(".tree.ul").add(function(){
        			return "li";
        		}).text("abcd");
        	}
       	} );
  	
        	
    }
}