var alertMessage = $('#alertMessage').val();
console.log(alertMessage);
// 지점
if (alertMessage == 'branchRegisterFail'){
	alert("지점등록 실패하였습니다.");
}
if (alertMessage == 'branchUpdateFail'){
	alert("지점 정보 수정 실패하였습니다.");
}

// 영화
if (alertMessage == 'movieRegisterFail'){
	alert("영화 등록 실패하였습니다.");
}
if (alertMessage == 'movieUpdateFail'){
	alert("영화 수정 실패하였습니다.");
} 

// 멤버
if (alertMessage == 'memberRegisterSuccess') {
	alert("회원가입에 성공하였습니다. 멤버가 되신 것을 축하합니다.");
}
if (alertMessage == 'memberRegisterFail') {
	alert("회원가입 실패");
}
if (alertMessage == 'memberUpdateSuccess'){
	alert("회원정보 수정 성공하였습니다.");
}
if (alertMessage == 'memberUpdateFail'){
	alert("회원정보 수정 실패하였습니다.");
}
if (alertMessage == 'memberResignSuccess'){
	alert("회원탈퇴 되었습니다. 그 동안 이용해 주셔서 감사합니다.");
}
if (alertMessage == 'memberResignFail'){
	alert("회원탈퇴 실패");
}
if (alertMessage == 'memberLoginFail') {
	alert("로그인이 실패하였습니다. 정보를 다시 확인해주세요");
}

// 티켓
if (alertMessage == 'ticketMmbIdSessionFail') {
	alert("로그인 후 예매 가능합니다");
}
if (alertMessage == 'ticketBuySuccess') {
	alert("티켓 구매 성공하였습니다");
}
if (alertMessage == 'ticketBuyFail') {
	alert("티켓 구매 실패하였습니다");
}

// 리뷰
if (alertMessage == 'reviewMmbIdSessionFail') {
	alert("로그인 후 리뷰 등록 가능합니다");
}
