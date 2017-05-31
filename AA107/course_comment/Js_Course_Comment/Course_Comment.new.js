

function doFirst(){
	
	//抓Css程式裡的屬性(東西)
	
	// var barsize = parseInt(window.getComputedStyle(defaultBar,null).width);
	 // var barsize = parseInt(window.getComputedStyle(defaultBar, null).width);
		// document.write(barsize);

	



	//先跟HTML畫面產生關聯
	myDIV = document.getElementById('myDIV');
	text = document.getElementById('text');
	modal = document.getElementById('modal-id');
	YYYY = document.getElementById('YYYY');
	
	
	//再建事件聆聽的功能
	
	text.addEventListener('click',textt,false);
	modal.addEventListener('click',modall,false);
	YYYY.addEventListener('click',ZZZZ,false);

	myDIV.addEventListener('click',playOrPause,false);
}




function ZZZZ(){
	
	 if (modal.style.display === 'block') {
		 modal.style.display = 'block';
	    } else {
	    	modal.style.display = 'block';
	    }
	
		
}



function textt(){
	
	 if (myDIV.style.display === 'none') {
		 myDIV.style.display = 'block';
	    } else {
	    	myDIV.style.display = 'none';
	    }
	
		
}
window.addEventListener('load',doFirst,false);