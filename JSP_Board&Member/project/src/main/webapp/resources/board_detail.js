async function getCommentListFromServer(bNo){//서버에 해당하는 bno 글에 대한 리스트를 달라고 요청
	try{
		const resp = await fetch('/cmt/list/'+bNo); ///cmt/list/260
		const cmtList= await resp.json();//위에 패치한 것의 결과값이 저장됨. 
		return cmtList;
	}catch(error){
		console.log(error);
	}
}
function spreadCommentList(result){
	console.log(result);
	let div= document.getElementById("accordionExample");
	div.innerHTML='';
	for(let i=0;i<result.length;i++){
		let html = '<div class="accordion-item">';
		html += `<h2 class="accordion-header" id="heading${i}">`;
		html +=`<button class="accordion-button" type="button" data-bs-toggle="collapse" `;
		html +=` data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">`; 
	    html += `${result[i].cNo}, ${result[i].bNo}, ${result[i].writer}</button></h2>`; 
		html += `<div id="collapse${i}" class="accordion-collapse collapse show" aria-labelledby="heading${i}" data-bs-parent="#accordionExample">`;
	    html += `<div class="accordion-body">`;
	    html += `<button type="button" data-cno="${result[i].cNo}" class="btn btn-sm btn-outline-warning cmtModBtn">%</button>`;
	    html +=`<button type="button" data-cno="${result[i].cNo}" class="btn btn-sm btn-outline-danger cmtDelBtn">X</button>`;
	    html +=`<input type="text" class="form-control" id="cmtText1" value="${result[i].content}">`;
	    html +=`${result[i].reg_at}`;
	    html +=`</div></div></div>`;
	    div.innerHTML+=html;//누적해서 담기
	}
}

async function removeCommentFromServer(cnoVal){
	try{
		const url='/cmt/remove/'+cnoVal;
		const config={
			method:'post'
		}
		const resp = await fetch(url,config);
		const result = resp.text();
		return result;
		//아래에 있는 구문으로 result가 이동
	}catch(error){
		console.log(error);
	}
}
async function updateCommentFromServer(cnoVal, cmtText1){
	try{
		const url = '/cmt/modify';
		const config={
		 method:'post',
         headers:{
            'Content-Type':'application/json; charset=utf-8'
            //application/x-www-form-urlencoded; charset=UTF-8 //쿼리스트링방식
         },
         body: JSON.stringify({cno: cnoVal, content: cmtText1})//JSON객체를 스트링화//직접 값을 여기서 만듦
		}
		const resp= await fetch(url, config);
		const result= await resp.text();//갖고 오는건 어차피 텍스트
		return result;
		
	}catch(error){
		console.log(error);
	}
}

document.addEventListener('click', (e) => {
	if(e.target.classList.contains('cmtModBtn')){
		//수정작업 호출
		let cnoVal = e.target.dataset.cno;
		console.log(cnoVal);
		let div = e.target.closest('div');//타겟을 기준으로 가장 가까운 div 찾기
		let cmtText1= div.querySelector('#cmtText1').value;
		updateCommentFromServer(cnoVal, cmtText1).then(result => {
			if(result >0){
				alert("댓글 수정 성공!!!");
				printCommentList(bnoVal);
			}
		})
	}
	if(e.target.classList.contains('cmtDelBtn')){
		//삭제작업 호출
		console.log(e.target.dataset.cno);
		let cnoVal = e.target.dataset.cno;
		console.log(cnoVal);
		removeCommentFromServer(cnoVal).then(result =>{
		if(result > 0){
            alert("댓글 삭제 성공!!!");
            printCommentList(bnoVal);
         }
		})
	}
})

function printCommentList(bNo){
	getCommentListFromServer(bNo).then(result => {//cmtList
		console.log(result);
		if(result.length > 0){//값이 있다면
			//화면에 뿌리는 실제 로직
			spreadCommentList(result);
			
		}else{
			let div = document.getElementById("accordionExample");
			div.innerText = "댓글이 없습니다.";
		}
	})
}

async function postCommentToServer(cmtData){
   try{
      const url ="/cmt/post";
      const config={
         method:'post',
         headers:{
            'Content-Type':'application/json; charset=utf-8'
            //application/x-www-form-urlencoded; charset=UTF-8 //쿼리스트링방식
         },
         body: JSON.stringify(cmtData)//JSON객체를 스트링화
      };
      const resp = await fetch(url, config);//이 구문이 보내는 구문
      const result = await resp.text();//out.print(isOk)가 여기서 오는거
      return result;//36번 라인
   }catch(error){
      console.log(error);
   }

}

document.getElementById('cmtAddBtn').addEventListener('click',()=>{
   const cmtText = document.getElementById('cmtText').value;
   console.log(cmtText);
   if(cmtText == null || cmtText==''){
      alert('댓글을 입력해주세요.');
      return false;
   }else{
      let cmtData = {
         bNo : bnoVal,
         writer : document.getElementById('cmtWriter').value,
         content : cmtText
      };

      postCommentToServer(cmtData).then(result => {
         if(result > 0){
            alert("댓글등록 성공!!!");
            document.getElementById('cmtText').value = "";
         }
         printCommentList(cmtData.bNo);
      })

   }

})