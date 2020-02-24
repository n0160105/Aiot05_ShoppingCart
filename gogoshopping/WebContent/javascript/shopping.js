
document.onreadystatechange = function () {
  
	if (document.readyState === "interactive") {
	
		var ul = document.getElementById("myul");
		var rownum = ul.getElementsByTagName("li").length - 1;
		document.getElementById("rownum").innerHTML = '物件數量: ' + rownum;
		
		var total = 0;
		for(i=0; i<rownum; i++){
			total += parseInt(document.getElementById("unitprice").innerText);	
		}
		
		document.getElementById("totalprice").innerHTML = total + ' 元';
		
	}

}