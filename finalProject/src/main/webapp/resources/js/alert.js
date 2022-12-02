var alert = $('#alert').val();
console.log(alert);

var alertBranchRegister = $('#alertBranchRegister').val();
console.log(alertBranchRegister);
// 지점
if (alertBranchRegister == 'branchRegisterFail'){
	alert("지점등록 실패하였습니다.");
}
if (alertBranchRegister == 'branchUpdateFail'){
	alert("지점 정보 수정 실패하였습니다.");
}

var alertMovieRegister = $('#alertMovieRegister').val();
console.log(alertMovieRegister);
// 영화
if (alertMovieRegister == 'movieRegisterFail'){
	alert("영화 등록 실패하였습니다.");
}
if (alertMovieRegister == 'movieUpdateFail'){
	alert("영화 수정 실패하였습니다.");
}

var alertMemberRegister = $('#alertMemberRegister').val();
console.log(alertMemberRegister);
// 멤버
if (alertMemberRegister == 'memberRegisterSuccess') {
	alert("회원가입에 성공하였습니다. 멤버가 되신 것을 축하합니다.");
}
if (alertMemberRegister == 'memberRegisterFail') {
	alert("회원가입 실패");
}

var alertMemberUpdate = $('#alertMemberUpdate').val();
console.log(alertMemberUpdate);
if (alertMemberUpdate == 'memberUpdateSuccess'){
	alert("회원정보 수정 성공하였습니다.");
}
if (alertMemberUpdate == 'memberUpdateFail'){
	alert("회원정보 수정 실패하였습니다.");
}

var alertMemberResign = $('#alertMemberResign').val();
console.log(alertMemberResign);
if (alertMemberResign == 'memberResignSuccess'){
	alert("회원탈퇴 되었습니다. 그 동안 이용해 주셔서 감사합니다.");
}
if (alertMemberResign == 'memberResignFail'){
	alert("회원탈퇴 실패");
}

var alertMemberLogin = $('#alertMemberLogin').val();
console.log(alertMemberLogin);
if (alertMemberLogin == 'memberLoginFail') {
	alert("로그인이 실패하였습니다. 정보를 다시 확인해주세요");
}

var alertTicketMmbId = $('#alertTicketMmbId').val();
console.log(alertTicketMmbId);
// 티켓
if (alertTicketMmbId == 'ticketMmbIdSessionFail') {
	alert("로그인 후 예매 가능합니다");
}

var alertTicketBuy = $('#alertTicketBuy').val();
console.log(alertTicketBuy);
if (alertTicketBuy == 'ticketBuySuccess') {
	alert("티켓 구매 성공하였습니다");
}
if (alertTicketBuy == 'ticketBuyFail') {
	alert("티켓 구매 실패하였습니다");
}

var alertReviewMmbIdSession = $('#alertReviewMmbIdSession').val();
console.log(alertReviewMmbIdSession);
// 리뷰
if (alertReviewMmbIdSession == 'reviewMmbIdSessionFail') {
	alert("로그인 후 리뷰 등록 가능합니다");
}
