define(function(require, exports, module) {
	require('bootstrap');
	module.exports = {
		getBtnValue : function() {
			document.getElementById('btn').onclick = function() {
				$('#username').val('李四');
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
		},
		getTextValue : function(){
			console.log($('#username').val());
		}
	};
	
});