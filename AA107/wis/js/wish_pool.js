$(document).ready(function() {
	$('.modal').modal();
	$('.tooltipped').tooltip({delay: 50});
});

function insertWisLike(e) {
	var btnLike = e.target;
//	console.log(btnLike.id);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				console.log("i'm back");
				if(xhr.responseText == 1){
					Materialize.toast('你已經收藏過此許願了哦', 4000);
				}else{
					Materialize.toast('成功收藏此許願!!!', 4000);
				}	
			}
		}
	}

//	console.log(document.getElementById("wis_no_forLike"+btnLike.id).value);
	
	var data_info = "wis_no=" + btnLike.id
				    +"&mem_no=" + document.getElementById("mem_no_forLike"+btnLike.id).value;
	var url = "InsertWisLike_ajax.jsp?";
	xhr.open("post", url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(data_info);
}

function init() {

	//收藏許願ajax
	var arrBtn = document.getElementsByName("btnLike");
//console.log(arrBtn.length);
	for(i = 0; i < arrBtn.length; i++){
		arrBtn[i].onclick = function(){
			insertWisLike(event);
		}		
	};
	

	CKEDITOR.replace('wis_cnt_teach', {
		toolbar : [
				{
					name : 'document',
					items : [ 'Save', 'DocProps', 'Preview', 'Print' ]
				},
				{
					name : 'clipboard',
					items : [ 'Cut', 'Copy', 'Paste', 'PasteText', '-', 'Undo',
							'Redo' ]
				}, {
					name : 'links',
					items : [ 'Link', 'Unlink' ]
				}, {
					name : 'styles',
					items : [ 'Styles', 'Format', 'Font', 'FontSize' ]
				}, {
					name : 'colors',
					items : [ 'TextColor', 'BGColor' ]
				}, {
					name : 'tools',
					items : [ 'Maximize' ]
				} ]
	});
	CKEDITOR.replace('wis_cnt_learn', {
		toolbar : [
				{
					name : 'document',
					items : [ 'Save', 'DocProps', 'Preview', 'Print' ]
				},
				{
					name : 'clipboard',
					items : [ 'Cut', 'Copy', 'Paste', 'PasteText', '-', 'Undo',
							'Redo' ]
				}, {
					name : 'links',
					items : [ 'Link', 'Unlink' ]
				}, {
					name : 'styles',
					items : [ 'Styles', 'Format', 'Font', 'FontSize' ]
				}, {
					name : 'colors',
					items : [ 'TextColor', 'BGColor' ]
				}, {
					name : 'tools',
					items : [ 'Maximize' ]
				} ]
	});

}

window.addEventListener('scroll', function(e) {
	var distanceY = window.pageYOffset || document.documentElement.scrollTop;
	if (distanceY > 100) {
		if (!$('header').hasClass('smaller')) {
			$('header').addClass('smaller');
		}
	} else {
		if ($('header').hasClass('smaller')) {
			$('header').removeClass('smaller');
		}
	}
	;
})

window.onload = init;
