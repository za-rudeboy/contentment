contentment
===========

This is a java applictaion that delivers content which can then be consumed by another application. It provides content 
through a REST webservice in JSON format. The content is Base64 encoded. Currently the service only delivers a single piece of content which is retrieved by either passing the content id or content path. 
e.g for the content with id = 100
http://localhost:8080/content-service/rest/GetContent/ById?contentId=100
or path = /fluidpage/rudyFluid.html
http://localhost:8080/content-service/rest/GetContent/ByPath?contentPath=/fluidpage/rudyFluid.html

Internally the app reads content based on metadata files which is a property file for each piece of content. It 
looks like:

file.path = /fluidpage/rudyFluid.html
content.path = /fluidpage/rudyFluid.html
content.id = 101
content.type = text/html

My plans are to create a seperate app which can be used to author the content as well as create it's metadata

