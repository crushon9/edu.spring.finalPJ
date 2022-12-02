// 지점
var alertBranchRegister = $('#alertBranchRegister').val();
console.log(alertBranchRegister);
if (alertBranchRegister == 'branchRegisterFail') {
	alert("지점등록 실패하였습니다.");
}

var alertBranchUpdate = $('#alertBranchUpdate').val();
console.log(alertBranchUpdate);
if (alertBranchUpdate == 'branchUpdateFail') {
	alert("지점 정보 수정 실패하였습니다.");
} else if (alertBranchUpdate == 'branchUpdateImmutableFail') {
	alert("해당 지점으로 등록된 스케줄이 있어 수정 불가합니다");
}

var alertBranchDelete = $('#alertBranchDelete').val();
console.log(alertBranchDelete);
if (alertBranchDelete == 'branchDeleteFail') {
	alert("지점 정보 삭제 실패하였습니다.");
} else if (alertBranchDelete == 'branchDeleteImmutableFail') {
	alert("해당 지점으로 등록된 스케줄이 있어 삭제 불가합니다");
}

// 영화
var alertMovieRegister = $('#alertMovieRegister').val();
console.log(alertMovieRegister);
if (alertMovieRegister == 'movieRegisterFail') {
	alert("영화 등록 실패하였습니다.");
}

var alertMovieUpdate = $('#alertMovieUpdate').val();
console.log(alertMovieUpdate);
if (alertMovieUpdate == 'movieUpdateFail') {
	alert("영화 수정 실패하였습니다.");
} else if (alertMovieUpdate == 'movieUpdateImmutableFail') {
	alert("해당 영화로 등록된 스케줄이 있어 수정 불가합니다");
}

var alertMovieDelete = $('#alertMovieDelete').val();
console.log(alertMovieDelete);
if (alertMovieDelete == 'movieDeleteFail') {
	alert("영화 삭제 실패하였습니다.");
} else if (alertMovieDelete == 'movieDeleteImmutableFail') {
	alert("해당 영화로 등록된 스케줄이 있어 삭제 불가합니다");
}

// 멤버
var alertMemberRegister = $('#alertMemberRegister').val();
console.log(alertMemberRegister);
if (alertMemberRegister == 'memberRegisterSuccess') {
	alert("회원가입에 성공하였습니다. 멤버가 되신 것을 축하합니다.");
} else if (alertMemberRegister == 'memberRegisterFail') {
	alert("회원가입 실패");
}

var alertMemberUpdate = $('#alertMemberUpdate').val();
console.log(alertMemberUpdate);
if (alertMemberUpdate == 'memberUpdateSuccess') {
	alert("회원정보 수정 성공하였습니다.");
} else if (alertMemberUpdate == 'memberUpdateFail') {
	alert("회원정보 수정 실패하였습니다.");
}

var alertMemberResign = $('#alertMemberResign').val();
console.log(alertMemberResign);
if (alertMemberResign == 'memberResignSuccess') {
	alert("회원탈퇴 되었습니다. 그 동안 이용해 주셔서 감사합니다.");
} else if (alertMemberResign == 'memberResignFail') {
	alert("회원탈퇴 실패");
}

var alertMemberLogin = $('#alertMemberLogin').val();
console.log(alertMemberLogin);
if (alertMemberLogin == 'memberLoginFail') {
	alert("로그인이 실패하였습니다. 정보를 다시 확인해주세요");
}

// 티켓
var alertTicketBuy = $('#alertTicketBuy').val();
console.log(alertTicketBuy);
if (alertTicketBuy == 'ticketBuySuccess') {
	alert("티켓 구매 성공하였습니다");
} else if (alertTicketBuy == 'ticketBuyFail') {
	alert("티켓 구매 실패하였습니다");
}

// 세션 알럿
var alertSession = $('#alertSession').val();
console.log(alertSession);
if (alertSession == 'ticketSessionFail') {
	alert("로그인 후 티켓 예매 가능합니다");
} else if (alertSession == 'reviewSessionFail') {
	alert("로그인 후 리뷰 등록 가능합니다");
}
