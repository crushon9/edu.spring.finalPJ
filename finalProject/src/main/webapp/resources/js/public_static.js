// 이 파일을 만든 이유 :
// 1. static 변수는 이곳에만 정의해두고 값 변경시 모든페이지에 동일하게 적용하기위해
// 2. 공통으로 많이 쓰이는 함수를 이곳에 정의하여 효율을 높이기 위해

// 지역이름을 td의 요소 길이만큼 반복하여 세팅
function publicSetBrcAreaName() {
  console.log('setBrcAreaName() 호출');
  var brcAreaArray = ['서울', '경기/강원', '부산/경상', '대전/충청', '광주/전라', '제주'];
  for (var i = 0; i < $('td').length; i++){
	// '#brc' + i : status.index로 부여된 tr(row) id
  	var brcArea = $('#brc' + i).children('.brcAreaTd').children('.brcArea').val();
  	$('#brc' + i).children('.brcAreaTd').children('.brcAreaName').html(brcAreaArray[brcArea - 1]);
  }
};

// DB에 저장된 타임 인덱스를 시간 String으로 변환하기 위한 변수
var publicScdTimeArray = 
	["07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", // 여기까지 조조할인
	"11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
	"15:00", "15:30", "16:00", "16:30",	"17:00", "17:30", "18:00", "18:30",
	"19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30",
	"23:00", "23:30", "00:00", "00:30", "01:00", "01:30", "02:00"];

// 선택 지역의 지점 가져오기
function publicGetBrcList() {
	var brcArea = $('#brcArea').val();
	var url = '/project/branch/list/' + brcArea; // REST API 방식 적용
	$.getJSON(
			url,
			function(data) {
				var brcList = '<select id="brcId" name="brcId" ><option>지점선택</option>';
				$(data).each(function() {
					brcList += '<option value="' + this.brcId + '">' + this.brcName + '</option>';
				});
				brcList += '</select>'
					$('#brcListOutput').html(brcList);
			}
	); // end getJSON
}
