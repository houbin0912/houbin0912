$(function() {
	$.post('/sys/user/info',function(result){
		if(result != null){
			$("#userNameTop").text(result.userName);
		}
	 })
		
});
function logout(){
	$.post('/sys/logout',function(result){
		if(result == true){
			 window.location.href = result.refUrl;
		}
	 })
}
		