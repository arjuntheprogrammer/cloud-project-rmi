<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
html {
	font-size: 14px;
	font-family: Arial, Helvetica, sans-serif;
}
</style>
<title></title>
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.common-material.min.css" />
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.min.css" />
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.mobile.min.css" />

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css" />


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script
	src="https://kendo.cdn.telerik.com/2017.1.223/js/kendo.all.min.js"></script>
<script src="js/Tree.js"></script>
<!-- editor files -->
<link rel="stylesheet" type="text/css" href="css/editor.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js"
	type="text/javascript" charset="utf-8"></script>


<style>
#upload {
	display: none
}

#sub {
	display: none
}
</style>


<script>
	$(function() {
		$("#upload_link").on('click', function(e) {
			e.preventDefault();

			$("#upload:hidden").trigger('click');

			console.log($("#upload:hidden").val());
		});

	});
	$("#upload:hidden").change(function() {
		//alert($(this).val());
		$("#sub:hidden").trigger('click');
	});
</script>

</head>
<body>

	<input type="hidden" id="current_div" value='${json}' />



	<div id="horizontal">
		<div id="left-pane" style="width: 500px; height: 600px;">
			<div class="tree"></div>
			<form id="form" action="/SimpleServletProject/SimpleServletPath"
				method="post" enctype="multipart/form-data"">
				<input class="up" id="upload" type="file" name="file" /> <a href=""
					id="upload_link">upload</a> <input type="submit" id="sub" />
			</form>
		</div>

		<div id="right-pane">
			<div id="editor">function foo(items) { var x = "All this is";
				return x; }</div>

		</div>




	</div>



</body>
<script>
	var editor = ace.edit("editor");

	editor.setTheme("ace/theme/xcode");
	editor.getSession().setMode("ace/mode/javascript");
</script>
</html>