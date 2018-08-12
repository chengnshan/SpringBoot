require.config({
	baseUrl : '../js/',
	shim : {
		'bootstrap' : {
			deps : [ 'jquery' ]
		},
		'com':{
			deps:['jquery'],
			exports:'com'
		}
	},
	paths : {
		'jquery' : 'jquery.min',
		'bootstrap' : 'bootstrap/js/bootstrap.min',
		'com':'myjs/com'
	}
});

require(["com","jquery","bootstrap"], function(com,$,bootstrap) {
	$('#btn').click(function(){
		alert(123);
		com.findCustomerInfo();
	});
});

define(['com' ], function(com) {
	var com = {
			findCustomerInfo : function(){
				$.ajax({
					type :'post',
					url:'../findCustomerInfo?timestatmp='+new Date(),
					dataType:'jsonp',
					crossDomain: true,
					jsonpCallback:"jsonpCall",
					contentType : 'application/x-www-form-urlencoded',
					data:{'name':'张三丰','address':'广东深圳'},
					success:function(result){
						console.log(result);
//						console.log($.parseJSON(result));
						var html="";
						$.each( result,function(index,item){
							html += '<tr><td>'+item.id+'</td><td>'+item.custNo+'</td><td>'+item.name+'</td><td>'
							+item.phone+'</td><td>'+item.birthday+'</td><td>'+item.createDate+'</td>'
							+"</tr>";
						});
						$("#tbody").html(html);
					}
				});
			}
	};
	return com;
});

