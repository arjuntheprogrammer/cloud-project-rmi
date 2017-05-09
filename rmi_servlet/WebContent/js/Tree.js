var j=0;
var str=""
var curr_file=""
	//  define("ace/ext/modelist",["require","exports","module"],function(e,t,n){"use strict";function i(e){var t=a.text,n=e.split(/[\/\\]/).pop();for(var i=0;i<r.length;i++)if(r[i].supportsFile(n)){t=r[i];break}return t}var r=[],s=function(e,t,n){this.name=e,this.caption=t,this.mode="ace/mode/"+e,this.extensions=n;var r;/\^/.test(n)?r=n.replace(/\|(\^)?/g,function(e,t){return"$|"+(t?"^":"^.*\\.")})+"$":r="^.*\\.("+n+")$",this.extRe=new RegExp(r,"gi")};s.prototype.supportsFile=function(e){return e.match(this.extRe)};
var o={c:"c_cpp",cpp:"c_cpp",java:"java",js:"javascript",jsp:"jsp",py:"python"}
$(document).ready(function() {
    $("#horizontal").kendoSplitter({
        panes: [
            { collapsible: true },
            { collapsible: true}
        ]
    });
    //populating tree
	var json = $('#current_div').val();
	var obj = JSON.parse($('#current_div').val());

	//var text='{"subDir":[{"name":"CBEA-Course Descriptionv2.pdf","type":"file"},{"name":"Course Project Deliverables.pdf","type":"file"},{"name":"abcd.cpp","type":"file"},{"name":"CREDEL Scope.docx","type":"file"},{"name":"List of Topics for Mini Project.pdf","type":"file"},{"subDir":[{"name":"Chapter 2.pdf","type":"file"},{"name":"jdbcv2.pdf","type":"file"},{"subDir":[{"name":"Client Interfaces.docx","type":"file"},{"name":"Distributed Object Applications.docx","type":"file"},{"name":"DS Obj n RMI.ppt","type":"file"},{"name":"es1-2p.pdf","type":"file"},{"name":"Exceptions In RMI.docx","type":"file"},{"name":"oracle link.txt","type":"file"},{"name":"Registry Interfaces.docx","type":"file"},{"name":"RMI Overview.docx","type":"file"},{"name":"RMI Wire Protocol.docx","type":"file"},{"name":"Server Interfaces.docx","type":"file"},{"name":"Stub.docx","type":"file"},{"name":"The Distributed and Nondistributed Models Contrasted.docx","type":"file"}],"name":"RMI & Distributed obj","type":"directory"},{"name":"Topic wise References.docx","type":"file"}],"name":"Reference Material","type":"directory"},{"name":"Sample Report to be submitted before T1.pdf","type":"file"},{"name":"Thumbs.db","type":"file"},{"name":"Tut-1.pdf","type":"file"},{"name":"Tut-2.pdf","type":"file"},{"name":"Tut-3.pdf","type":"file"},{"name":"Tut-4.pdf","type":"file"}],"name":"CBEA","type":"directory"}';
	//var obj = JSON.parse(text);
	//var obj2=JSON.parse(text2);
	console.log(obj.type);
	$('.tree').append(input(obj, 0,"") );
    
	//styling tree
    $('.tree li').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
        }
        e.stopPropagation();
    });
    
    var editor = ace.edit("editor");
    editor.setTheme("ace/theme/xcode");
    editor.getSession().setMode("ace/mode/c_cpp");
    
    

    
    //on clicking file with ajax
    var id=""
    $('.tree li.parent_li > span').on('click', function (e) {
    	j=0
    	id=""
    	str=""
        id=$(this).attr('id');
        console.log(id)
        console.log(id.length)
    	nt=0
    	
    	while(j<id.length && id[j]!='.' ){
    		nt=nt*10+parseInt(id[j]);
    		j++
    	}
        
    	j++
    	console.log(nt)
    	//filling editor
    	
        var to=id_to_path(id,obj) 
    	console.log(to)
    	curr_file=to
    	var params = {
    	    path: to,
    	 };
    	
    	var editor_text=""
    	$.post("/rmi_servlet/FileServlet", $.param(params), function(response) {
    	    editor_text=String(response)
    	    
	        
	        var i=0;
	    	while(i<curr_file.length && curr_file[i]!='.' ){
	    		i++;
	    	}
	    	i++;
	    	var ext=""
	    	for( ;i<curr_file.length-1 ;i++ ){
	    		ext+=curr_file[i]
	    	}
	        var mode=o[ext]
	        
	        editor.session.setMode(mode);
    	    editor.getSession().setValue(editor_text)
    	});
        
    });
    
    //saving
    
    editor.commands.addCommand({
        name: 'myCommand',
        bindKey: {win: 'Ctrl-S',  mac: 'Command-S'},
        exec: function(editor) {
        	var dat=editor.getSession().getValue()

        	var params = {
        	    path: curr_file,
        	    data: dat
        	 };
        	if(curr_file!=""){
    			$.post("/rmi_servlet/SaveServlet", $.param(params), function(response) {
    				console.log("saved")
    				snack();
    			});
        	}
            
        },
        readOnly: false // false if this command should not apply in readOnly mode
    });
    


	//on click upload
    $(".up_l").on('click', function(e){
        e.preventDefault();
        
        j=0
    	nt=0
    	var id1=$(this).parent().children("span").attr('id')
    	while(j<id1.length && id1[j]!='.' ){
    		nt=nt*10+parseInt(id1[j]);
    		j++
    		console.log("out", j)
    	}
    	j++
        console.log("out", j)
    	
        var to1=id_to_upload_path(id1,obj,j)
        console.log("to1=", to1);
    	
		$(".id_name").val(to1)
        $(".up").trigger('click');
        
        
    });
    
	$(".up").change(function(){
		$(".sub").trigger('click');
	});

	//on click upload
    $(".save_as").on('click', function(e){
    	var dat=editor.getSession().getValue()

        j=0
    	nt=0
    	var id1=$(this).parent().children("span").attr('id')
    	while(j<id1.length && id1[j]!='.' ){
    		nt=nt*10+parseInt(id1[j]);
    		j++
    	}
    	j++
    	
        var to1=id_to_upload_path(id1,obj,j)
        var filename = prompt("Please enter your file name");
    	to1+='/'+filename;
        var params = {
    	    path: to1,
    	    data: dat
    	 };
		$.post("/rmi_servlet/UploadServlet", $.param(params), function(response) {
			alert("saved")
		});

        
        
    });
    
});


function input(obj, level, str) {	
	
	if(obj.type==="directory"){
		var ul=$('<ul/>');
		str+=level+"."
		var li = $('<li/>')
	    .appendTo(ul);
	    var t=$('<span id='+str+' />').append($('<i/>').addClass('icon-minus-sign'));
	    t.append(obj.name);
	    
	    li.append(t);
	    //li.append('<nav id="primary_nav_wrap"><ul class ="per"><li><a href="#">options</a><ul><li><a href="#">SM 1</a></li><li><a href="#">S 2</a></li></li> </ul></nav>');
		li.append('<a href="" class="up_l" id="upload_link">Upload</a>')
		li.append('<a href="" class="save_as" id="save">Save as</a>')

		$.each(obj.subDir, function(i){	
					
			li.append(input(obj.subDir[i], i,str))
				    	
		});
		return ul;
	}
	else if(obj.type==="file"){
		var ul=$('<ul/>');
		str+=level+"."
		var li = $('<li/>')
	    .appendTo(ul);
	    var t=$('<span id='+str+' />').append($('<i/>').addClass('icon-leaf'));
	    t.append(obj.name);
	    
	    li.append(t);
		return ul;
	}

}

function id_to_path(id,obj) {	
		var nt=0;
		
		while(j<id.length && id[j]!='.' ){
			nt=nt*10+parseInt(id[j]);
			j++
		}		
		if(id.length==j){
			console.log("here	")
		}
		j++
		var str1="";
		if(  obj.type==="directory" ){
			 str1=id_to_path(id,obj.subDir[nt]) ;	
		}
		return obj.name+'/'+str1
	
}

function id_to_upload_path(id,obj,j) {	
	var nt=0;
	
	while(j<id.length && id[j]!='.' ){
		nt=nt*10+parseInt(id[j])
		j++
	}
	var str1="";
	if(  obj.type==="directory" ){
		if(obj.subDir[nt].type!="file"){
		 str1=id_to_upload_path(id,obj.subDir[nt],j)
		}
	}
	return obj.name+'/'+str1

}

function snack() {
    // Get the snackbar DIV
    var x = document.getElementById("snackbar")

    // Add the "show" class to DIV
    x.className = "show";

    // After 3 seconds, remove the show class from DIV
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

