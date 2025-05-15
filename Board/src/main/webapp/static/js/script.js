/**
 *  javascript - script.js
 */


/**
 *  회원 가입 - 유효성 검사
 */
async function checkUser() {
	let form = document.joinForm
	// 아이디 중복 확인
	let idDuplicated = await idCheck()
	if( idDuplicated ) {
		alert('중복된 아이디 입니다.')
		return false
	}
	
	if( validateUser() ) {
		form.submit()
		// 유효성 검사 통과 
		return true
	}
}


function validateUser() {
	let form = document.joinForm
	let username = form.username
	let password = form.password
	let password_confirm = form.password_confirm
	let name = form.name
	let email = form.email

	let msg = ''
	
	// 아이디 유효성 검사
	let usernameCheck = /^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{5,20}$/
	msg = '아이디는 한글, 영문자, 숫자 5~20 자로 입력해주세요.'
	if (!check(usernameCheck, username, msg)) return false

	// 비밀번호 유효성 검사
	let passwordCheck = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/
	msg = '비밀번호는 영문자, 숫자, 특수문자를 포함하여 8자 이상으로 입력해주세요.'
	if (!check(passwordCheck, password, msg)) return false

	// 비밀번호 확인 검사
	msg = '비밀번호와 비밀번호 확인이 불일치합니다.'
	if( password.value != password_confirm.value ) {
		alert(msg)
		return false	
	}

	// 이름 유효성 검사
	let nameCheck = /^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{1,20}$/
	msg = '이름은 한글 또는 영문 20자 이내로 입력해주세요.'
	if (!check(nameCheck, name, msg)) return false

	// 이메일 유효성 검사
	let emailCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i
	msg = '이메일 형식이 올바르지 않습니다.'
	if (!check(emailCheck, email, msg)) return false
	
	return true
}

// 정규표현식 유효성 검사 함수
function check(regExp, element, msg) {

	if(regExp.test(element.value)) {
		return true
	}
	alert(msg)
	element.select()
	element.focus()
	return false
}


// 아이디 중복 확인
function idCheck() {
	let username = document.getElementById('username').value
    let url = `/Board/users/idCheck`
    
    let data = {
		'username' : username,
	}
	
	return new Promise((resolve, reject) => {
	    // jQuery 로 AJAX 요청
	    $.ajax({
	        type            : 'GET',                 // 요청 메소드
	        url             : url,                    // 요청 URL
	        data            : data,   // 요청 데이터
	        contentType     : 'application/json',     // 요청 데이터 타입
	        dataType        : 'html',                 // 응답 데이터 타입
	        // 요청 성공 
	        success         : function(response, status) {
	            // response : 응답 데이터
	            // status   : 응답 상태
	            if( response == '' ) {
	                alert('응답 메시지가 없습니다')
	            }
	            resolve(response == 'true' ? true : false)
	        },
	        // 에러
	        error           : function(xhr, status) {
	            // xhr      : XMLHttpRequest 객체
	            // status   : 응답 상태
	            alert('에러 발생')
	            reject('error')
	        }
	        
	    })
	})
	
}