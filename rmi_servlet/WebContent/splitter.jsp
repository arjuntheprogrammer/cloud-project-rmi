<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <style>html { font-size: 14px; font-family: Arial, Helvetica, sans-serif; }</style>
    <title></title>
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.common-material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.223/styles/kendo.material.mobile.min.css" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	
    <script src="https://kendo.cdn.telerik.com/2017.1.223/js/kendo.all.min.js"></script>
	<script src="js/Tree.js"></script>
	<!-- editor files -->
	<link rel="stylesheet" type="text/css" href="css/editor.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ace.js" type="text/javascript" charset="utf-8"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.2.6/ext-modelist.js" type="text/javascript" charset="utf-8"></script>
	
    
</head>
<body>
	<input type="hidden" id="current_div" value='${json}' />


        <div id="example">
                
                    <div id="horizontal" >
                        <div id="left-pane">
                        	<div class="tree"></div>
                        </div>
 
                        <div id="right-pane">
							<div id="editor">function foo(items) {
							    var x = "All this is syntax highlighted";
							    return x;
								}
							</div>
                        </div>
                        
                        <form id="form" action="/rmi_servlet/login.do" method="post">
						
							<div>
								FileName : <input name="path" id="myText" type="text" /> <input
									class="submit" type="submit" id="download_file">Download
								</button>
							</div>
						</form>

                    
            		</div>

            <style>

                #horizontal {
                    margin: 0 auto;
                    height: 100vh;
                }

                #left-pane    { background-color: rgba(60, 70, 80, 0.05);}
                #right-pane  { background-color: rgba(60, 70, 80, 0.05); }

                .pane-content {
                    padding: 0 10px;
                }
            </style>
        </div>
        
        
        <form id ="form" action="/rmi_servlet/UploadServlet" method="post" enctype="multipart/form-data">
		    <input class="up" id="upload"  type="file" name="file" />
			<a href="" class="up_l" id="upload_link_hid">upload</a>
			<input class="id_name" type="hidden" name="c" value="abcd"  /> 
		    <input type="submit" class="sub" id="submit" />
		</form>
		
		<div id="snackbar">Saved...</div>
		
		<style>
			#upload{
		    display:none}
		    #submit{
		    display:none}
		    #upload_link_hid{
		    display:none}		    
		</style>
				 
		
		
</body>
</html>
