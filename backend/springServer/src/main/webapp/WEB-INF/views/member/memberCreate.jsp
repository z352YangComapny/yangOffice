<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/member.css" />
<%--모달 시작--%>
<div class="modal fade" id="memberAgree1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
				<h4 class="modal-title" id="myModalLabel">회원약관</h4>
			</div>
			<div class="modal-body" style="height: 500px; overflow: auto;">
				본 이용약관은 Yang Company (이하 "회사" 또는 "저희")가 제공하는 SSOY STORY (이하 "서비스")의 이용 조건을 규정하는 것을 목적으로 합니다. 회원 여러분은 본 약관을 읽고 이해하신 후 서비스를 이용해 주시기 바랍니다. 이용 약관에 동의하지 않으실 경우, 서비스 이용을 중단하시기 바랍니다.
				<br />
				<br />
				1. 회원의 의무 및 권리
				<br />
				1.1 회원은 본인의 개인정보를 정확하게 제공해야 하며, 제공한 정보가 변경되었을 경우에는 즉시 수정해야 합니다.
				<br />
				1.2 회원은 서비스를 이용함에 있어서 다음 각 호의 행위를 하여서는 아니 됩니다:
				<br />
				- 불법적인 목적으로 서비스를 이용하는 행위
				<br />
				- 타인의 개인정보를 무단으로 수집, 저장, 유포하는 행위
				<br />
				- 저작권 및 지적재산권을 침해하는 행위
				<br />
				<br />
				2. 사진피드 등록 및 방명록에 관한 사항
				<br />
				2.1 회원은 본인이 업로드한 사진 및 피드 등록에 대한 책임을 가집니다. 모니터링 과정에서 부적절한 내용이 확인될 경우, 우리는 해당 내용을 삭제하고 회원에게 알림을 전송할 수 있습니다.
				<br />
				<br />
				3. 회원 정보의 활용
				<br />
				3.1 회원 정보는 본인인증 및 서비스 제공을 위한 목적으로만 활용되며, 회원의 동의 없이 제3자에게 제공되지 않습니다.
				<br />
				<br />
				4. 책임의 한계
				<br />
				4.1 회사는 천재지변 등 불가항력적인 사유로 인해 서비스가 중단될 경우에는 이로 인한 손해에 대해 책임을 지지 않습니다.
				<br />
				본 약관은 2023.08.25에 개정되었습니다.
			</div>
			<div class="modal-footer">
				<div class="left-side">
					<button type="button" class="btn btn-default btn-simple" data-dismiss="modal">Never mind</button>
				</div>
				<div class="divider"></div>
				<div class="right-side">
					<button type="button" class="btn btn-primary btn-simple agree-confirm">동의</button>
					<button type="button" class="btn btn-secondary btn-simple agree-close">닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="memberAgree2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
				<h4 class="modal-title" id="myModalLabel">개인정보 이용 및 수집에 대한 안내</h4>
			</div>
			<div class="modal-body" style="height: 500px; overflow: auto;">
				적용 시작일: 2022년 6월 30일
				<br/>
				YANG COMPANY(이하 ‘회사’)의 개인정보처리방침은 아래의 내용을 담고 있습니다.
				<br/>
				제1조 목적<br/>
				제2조 수집하는 개인정보의 항목<br/>
				제3조 개인정보 수집에 대한 동의<br/>
				제4조 개인정보의 수집 및 이용목적<br/>
				제5조 쿠키에 의한 개인정보 수집<br/>
				제6조 목적 외 사용 및 제3자에 대한 제공 및 공유
				제7조 개인정보의 열람, 정정
				제8조 개인정보 수집, 이용, 제공에 대한 동의철회(회원탈퇴)
				제9조 수집하는 개인정보의 보유 및 이용기간
				제10조 개인정보보호를 위한 기술 및 관리적 대책
				제11조 링크사이트
				제12조 개인정보의 위탁처리
				제13조 이용자의 권리와 의무
				제14조 의견수렴 및 불만처리
				제15조 개인정보관리책임자 및 담당자
				제16조 아동의 개인정보보호
				제17조 고지의 의무

				제1조 (목적)
				1.개인정보”란 생존하는 개인에 관한 정보로서 당해 정보에 포함되어 있는 성명, 주민등록번호 등의 사항에 의하여 당해 개인을 식별할 수 있는 정보(당해 정보 만으로는 특정 개인을 식별할 수 없더라도 다른 정보와 용이하게 결합하여 식별할 수 있는 것을 포함합니다)를 말합니다.
				2.회사는 귀하의 개인정보보호를 매우 중요시하며, 『개인정보보호법』, 『정보통신망 이용촉진 및 정보보호에 관한 법률』 상의 개인정보보호규정 및 정보통신부가 제정한 『개인정보보호지침』을 준수하고 있습니다. 회사는 개인정보처리방침을 통하여 귀하께서 제공하시는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며 개인정보보호를 위해 어떠한 조치가 취해지고 있는지 알려드립니다.
				3.회사는 개인정보처리방침을 홈페이지 첫 화면 등에 공개함으로써 귀하께서 언제나 용이하게 보실 수 있도록 조치하고 있습니다.
				4.회사는 개인정보처리방침의 지속적인 개선을 위하여 개인정보처리방침을 개정하는데 필요한 절차를 정하고 있습니다. 그리고 개인정보처리방침을 개정하는 경우 버전번호 등을 부여하여 개정된 사항을 귀하께서 쉽게 알아볼 수 있도록 하고 있습니다.

				제2조 (수집하는 개인정보의 항목)
				회사는 맞춤형 서비스를 제공하기 위하여 회원서비스 및 제휴사를 통해 이용자의 정보를 수집하고 있습니다. 회사의 회원제 서비스를 이용하시고자 할 경우 다음의 정보를 입력해 주셔야 하며 선택항목을 입력하시지 않았다 하여 서비스 이용에 제한은 없습니다.
				1.회원 가입 시 수집하는 개인정보의 범위
				①필수항목 : 휴대폰번호, 거주지 주소
				②선택항목 : 공동현관 비밀번호
				2.서비스 이용과정이나 사업처리과정에서의 자동생성 정보
				①서비스 이용기록, 접속로그, 쿠키, 접속 IP정보, 결제기록, 이용정지기록, 기기고유번호(디바이스 아이디 또는 IMEI)

				제3조 (개인정보 수집에 대한 동의)
				귀하께서는 ‘제2조(수집하는 개인정보의 항목)’ 상 개인정보의 수집에 대해 동의를 거부할 수 있습니다. 다만, 거부하는 경우, 회원서비스 이용을 위한 회원가입이 불가합니다. 회사는 다음 각 호의 어느 하나에 해당하는 경우에는 법령에 따라 이와 같은 동의 없이 이용자의 개인정보를 수집∙이용할 수 있습니다.
				1.정보통신서비스의 제공에 관한 계약을 이행하기 위하여 필요한 개인정보로서 경제적 · 기술적인 사유로 통상적인 동의를 받는 것이 뚜렷하게 곤란한 경우
				2.정보통신서비스의 제공에 따른 요금정산을 위하여 필요한 경우
				3.그 밖에 법률에 특별한 규정이 있는 경우

				제4조 (개인정보의 수집 및 이용목적)
				1.회사는 다음과 같은 목적을 위하여 개인정보를 수집하고 있으며 목적이 변경될 경우에는 사전에 이용자의 동의를 구하도록 하겠습니다.
				①휴대폰번호 : 고지사항 전달, 본인 의사 확인, 불만 처리 등 원활한 의사소통 경로의 확보, 새로운 서비스/신상품이나 이벤트 정보 안내, 서비스 이용에 대한 리뷰 작성 권유, 그 밖에 회사가 제공하는 서비스 안내 및 광고성 정보 전송, 특정 상품 구매 후속 서비스 안내
				②거주지 주소 : 이용자의 수거요청 등에 따른 수거 및 배송을 위한 주소지의 확보
				③그 외 선택항목 : 개인맞춤 서비스를 제공하기 위한 자료

				제5조 (쿠키에 의한 개인정보 수집)
				1.쿠키(cookie)는 웹사이트가 귀하의 컴퓨터 브라우저(인터넷 익스플로러 등)로 전송하는 소량의 정보입니다. 회사는 귀하에 대한 정보를 저장하고 수시로 찾아내는 ‘쿠키(cookie)’를 사용합니다. 쿠키는 귀하의 컴퓨터는 식별하지만 귀하를 개인적으로 식별하지는 않습니다. 또한 귀하는 쿠키에 대한 선택권이 있습니다. 웹 또는 모바일 브라우저 상단의 도구 &gt; 인터넷옵션 탭(option tab) 등에서 모든 쿠키를 다 받아들이거나, 쿠키가 설치될 때 통지를 보내도록 하거나, 아니면 모든 쿠키를 거부할 수 있는 선택권을 가질 수 있습니다.
				2.회사의 쿠키(cookie) 운용
				①접속빈도 또는 머문 시간 등을 분석하여 이용자의 취향과 관심분야를 파악하여 타겟(target) 마케팅에 활용
				②회원들의 습관을 분석하여 서비스 개편 등의 척도

				제6조 (목적 외 사용 및 제3자에 대한 제공 및 공유)
				1.회사는 귀하의 개인정보를 「개인정보의 수집목적 및 이용목적」에서 고지한 범위 내에서 사용하며, 동 범위를 초과하여 이용하거나 타인 또는 타기업, 기관에 제공하지 않습니다. 특히 다음의 경우는 주의를 기울여 개인정보를 이용 및 제공할 것입니다.
				① 제휴 관계: 서비스 및 아파트 시설 편의 서비스 이용계약의 이행을 위하여 귀하의 개인정보를 제휴사에게 제공하거나 제휴사와 공유할 수 있습니다
				-개인정보를 제공받는 자: 제휴 서비스 제공사, 제휴 아파트 관리 사무소
				-개인정보를 제공받는 자의 개인정보 이용목적: 제휴 서비스 제공
				-제공하는 개인정보의 항목: 휴대폰번호, 거주지 주소, 서비스 이용 내역
				-개인정보보유 및 이용기간: 서비스 제공 완료 후 6개월
				-회사 서비스의 특성 상 수시로 발생하는 서비스 제공업체와의 제휴로 인하여 “제공받는 자” 관련 개인정보처리방침 개정에 어려움이 있습니다. 따라서 서비스 제공 업체를 제휴사 리스트 부분에 안내합니다.
				②매각, 인수합병 등: 서비스제공자의 권리와 의무가 완전 승계 이전되는 경우 반드시 사전에 정당한 사유와 절차에 대해 상세하게 고지할 것이며 이용자의 개인정보에 대한 동의 철회의 선택권을 부여합니다.
				2.고지 및 동의 방법은 공지사항 메뉴를 통해 최소 7일 이전부터 고지합니다.
				3.다음은 예외로 합니다.
				①관계법령에 의하여 수사상의 목적으로 관계기관으로부터의 요구가 있을 경우
				②통계작성, 학술연구나 시장조사를 위하여 특정 개인을 식별할 수 없는 형태로 광고주 협력사나 연구단체 등에 제공하는 경우
				③기타 관계법령에서 정한 절차에 따른 요청이 있는 경우
				④그러나 예외 사항에서도 관계법령에 의하거나 수사기관의 요청에 의해 정보를 제공한 경우에는 이를 당사자에게 고지하는 것을 원칙으로 운영하고 있습니다. 법률상의 근거에 의해 부득이하게 고지를 하지 못할 수도 있습니다. 본래의 수집목적 및 이용목적에 반하여 무분별하게 정보가 제공되지 않도록 최대한 노력하겠습니다.

				제7조 (개인정보의 열람, 정정)
				1.귀하는 언제든지 등록되어 있는 귀하의 개인정보를 열람하거나 정정하실 수 있습니다. 개인정보 열람 및 정정을 하고자 할 경우에는 마이페이지를 통해 직접 열람 또는 정정하거나, 개인정보관리책임자 및 담당자에게 서면, 전화 또는 이메일 등으로 연락하시면 지체 없이 조치하겠습니다
				2.귀하가 개인정보의 오류에 대한 정정을 요청한 경우, 정정을 완료하기 전까지 당해 개인정보를 이용 또는 제공하지 않습니다.
				3.잘못된 개인정보를 제 3 자에게 이미 제공한 경우에는 정정 처리결과를 제 3 자에게 지체 없이 통지하여 정정하도록 조치하겠습니다.

				제8조 (개인정보 수집, 이용, 제공에 대한 동의철회[회원탈퇴])
				1.회원가입 등을 통해 개인정보의 수집, 이용, 제공에 대해 귀하께서 동의하신 내용을 귀하는 언제든지 철회하실 수 있습니다. 동의철회는 『탈퇴』를 클릭하거나 개인정보관리책임자에게 팩스, 우편, 고객센터, 전화 등으로 연락하시면 회원탈퇴 신청 시점으로부터 90 일 동안 재가입 방지를 위한 개인정보보존 이후 개인정보의 삭제 등 필요한 조치를 하겠습니다. 동의 철회를 하고 개인정보를 파기하는 등의 조치를 취한 경우에는 그 사실을 귀하께 지체 없이 통지하도록 하겠습니다.
				2.회사는 개인정보의 수집에 대한 동의철회(회원탈퇴)를 개인정보를 수집하는 방법보다 쉽게 할 수 있도록 필요한 조치를 취하겠습니다.

				제9조 (수집하는 개인정보의 보유 및 이용기간)
				1.회사는 이용자로부터 개인정보를 수집 시에 동의 받은 개인정보 보유 · 이용기간 내에서 개인정보를 처리·보유합니다.
				①회원 정보: 회원탈퇴 후 90 일까지
				②비회원 정보: 업무 목적 달성 시까지
				③법령 위반에 따른 수사∙조사 등이 진행중인 경우에는 해당 건 종료 시까지
				④서비스 이용에 따른 채권·채무관계 정산 시까지
				2.1년간 회원의 서비스 이용 기록이 없는 경우, 『정보통신망 이용촉진 및 정보보호 등에 관한 법률』 제 29 조에 근거하여 회원에게 사전 통지하고 개인정보를 별도로 분리하여 휴면 계정 전환일로부터 3년간 보유합니다
				3.회사는 회원의 개인정보를 서비스 이용 시점부터 서비스를 제공하는 기간 동안에만 제한적으로 이용하고 있습니다.
				4.회원 탈퇴 또는 회원 자격 정지 후 회원 재가입, 임의해지 등을 반복적으로 행하여 회사가 제공하는 할인쿠폰, 이벤트 혜택 등으로 경제상의 이익을 취하거나 이 과정에서 명의를 무단으로 사용하는 등 편법행위 또는 불법행위를 하는 회원을 차단하고자 회사는 회원 탈퇴 후 90일간 회원의 정보를 보관합니다.
				5.본조 제2항 및 제3항에서 명시한 회원의 개인정보 보존기간이 경과되면, 회사는 종이에 출력된 개인정보는 분쇄기로 분쇄하거나 외부 업체를 통하여 분쇄하고, 전자적 파일 형태로 저장된 개인정보는 재사용할 수 없도록 기술적 방법을 사용하여 삭제 처리합니다.
				6. 본조 제1항 내지 제4항에도 불구하고 다음과 같이 거래 관련 권리 의무 관계의 확인 등을 이유로 일정기간 보유하여야 할 필요가 있는 개인정보의 경우, 회사는 하기 명시된 법령에 따라 해당 개인정보를 보관합니다.
				①서비스 이용 관련 개인정보 보존 근거: 통신비밀보호법 보존 기간 3개월
				②표시/광고에 관한 기록 보존 근거: 전자상거래 등에서의 소비자보호에 관한 법률 보존 기간 6개월
				③계약 또는 청약철회 등에 관한 기록 보존 근거: 전자상거래 등에서의 소비자보호에 관한 법률 보존 기간 5년
				④대금결제 및 재화 등의 공급에 관한 기록 보존 근거: 전자상거래 등에서의 소비자보호에 관한 법률 보존 기간 5년
				⑤소비자의 불만 또는 분쟁처리에 관한 기록 보존 근거: 전자상거래 등에서의 소비자보호에 관한 법률 보존 기간 3년
				⑥전자금융 거래에 관한 기록 보존 근거: 전자금융거래법 보존 기간 5년
				7.귀하의 동의를 받아 보유하고 있는 거래정보 등을 귀하께서 열람을 요구하시는 경우 회사는 지체 없이 그 열람 확인 할 수 있도록 조치합니다.

				제10조 (개인정보보호를 위한 기술 및 관리적 대책)
				1.기술적 대책: 회사는 귀하의 개인정보를 취급함에 있어 개인정보가 분실, 도난, 누출, 변조 또는 훼손되지 않도록 안전성 확보를 위하여 다음과 같은 기술적 대책을 강구하고 있습니다.
				①귀하의 개인정보는 비밀번호에 의해 보호되며 파일 및 전송데이터를 암호화하거나 파일 잠금기능(Lock)을 사용하여 중요한 데이터는 별도의 보안 기능을 통해 보호되고 있습니다.
				②회사는 백신프로그램을 이용하여 컴퓨터바이러스에 의한 피해를 방지하기 위한 조치를 취하고 있습니다. 백신프로그램은 주기적으로 업데이트되며 갑작스러운 바이러스가 출현할 경우 백신이 나오는 즉시 이를 제공함으로써 개인정보가 침해되는 것을 방지하고 있습니다.
				③회사는 암호알고리즘을 이용하여 네트워크 상의 개인정보를 안전하게 전송할 수 있는 보안장치(SSL)를 채택하고 있습니다.
				④해킹 등 외부침입에 대비하여 각 서버마다 침입차단시스템 및 취약점 분석시스템 등을 이용하여 보안에 만전을 기하고 있습니다.
				2.관리적 대책
				①회사는 귀하의 개인정보에 대한 접근권한을 최소한의 인원으로 제한하고 있습니다. 그 최소한의 인원에 해당하는 자는 다음과 같습니다.
				- 이용자를 직접 상대로 하여 마케팅 업무를 수행하는 자
				- 고객의 불만 및 이용 문의 처리 업무를 수행하는 자
				- 개인정보관리책임자 및 담당자 등 개인정보관리업무를 수행하는 자
				- 기타 업무상 개인정보의 취급이 불가피한 자
				②입사 시 전 직원의 보안서약서를 통하여 사람에 의한 정보유출을 사전에 방지하고 개인정보처리방침에 대한 이행사항 및 직원의 준수여부를 감사하기 위한 내부절차를 마련하고 있습니다.
				③개인정보 관련 취급자의 업무 인수인계는 보안이 유지된 상태에서 철저하게 이뤄지고 있으며 입사 및 퇴사 후 개인정보 사고에 대한 책임을 명확화하고 있습니다.
				④회사는 이용자 개인의 실수나 기본적인 인터넷의 위험성 때문에 일어나는 일들에 대해 책임을 지지 않습니다. 회원 개개인이 본인의 개인정보를 보호하기 위해서 자신의 ID와 비밀번호를 적절하게 관리하고 여기에 대한 책임을 져야 합니다.
				⑤그 외 내부 관리자의 실수나 기술관리 상의 사고로 인해 개인정보의 상실, 유출, 변조, 훼손이 유발될 경우 회사는 즉각 귀하께 사실을 알리고 적절한 대책과 보상을 강구할 것입니다.

				제11조 (링크사이트)
				회사는 귀하께 다른 회사의 웹사이트 또는 자료에 대한 링크를 제공할 수 있습니다. 이 경우 회사는 외부사이트 및 자료에 대한 아무런 통제권이 없으므로 그로부터 제공받는 서비스나 자료의 유용성에 대해 책임질 수 없으며 보증할 수 없습니다. 회사가 포함하고 있는 링크를 클릭(click)하여 타 사이트(site)의 페이지로 옮겨갈 경우 해당 사이트의 개인정보처리방침은 회사와 무관하므로 새로 방문한 사이트의 정책을 검토해 보시기 바랍니다.

				제12조 (개인정보의 위탁처리)
				회사는 서비스 향상을 위해서 귀하의 개인정보를 외부에 수집/취급/관리 등을 위탁하여 처리할 수 있습니다.
				1.개인정보의 처리를 위탁하는 경우에는 위탁기관 및 그 사실을 홈페이지를 통해 미리 귀하에게 고지하겠습니다. 다만, 재화 또는 서비스를 홍보하거나 판매를 권유하는 업무를 위탁하는 경우에는 이용자에게 개별적으로 이메일 주소 등을 통해 이용자에게 개별 통지하되, 회사가 과실 없이 서면, 전자우편 등의 방법으로 위탁하는 업무의 내용과 수탁자를 귀하에게 알릴 수 없는 경우에 해당사항을 홈페이지에 30일 이상 게시하겠습니다.
				2.개인정보의 처리를 위탁하는 경우에는 위탁계약 등을 통하여 서비스제공자의 개인정보보호 관련 지시엄수, 개인정보에 관한 비밀유지를 위하여 최선을 다하겠습니다.
				3.제3자 제공의 금지 및 사고 시의 책임부담, 위탁기간, 처리 종료후의 개인정보의 반환 또는 파기 등을 명확히 규정하고 당해 계약내용을 서면 또는 전자적으로 보관하겠습니다.
				4.회사의 개인정보 위탁처리 기관 및 위탁업무 내용은 아래와 같습니다.
				수탁업체(위탁업무 내용):
				NICEPAYMENTS(전자결제 수단 제공, 본인확인)

				제13조 (이용자의 권리와 의무)
				1.귀하의 개인정보를 최신의 상태로 정확하게 입력하여 불의의 사고를 예방해 주시기 바랍니다. 이용자가 입력한 부정확한 정보로 인해 발생하는 사고의 책임은 이용자 자신에게 있으며 타인 정보의 도용 등 허위정보를 입력할 경우 회원자격이 상실될 수 있습니다.
				2.귀하는 개인정보를 보호받을 권리와 함께 스스로를 보호하고 타인의 정보를 침해하지 않을 의무도 가지고 있습니다. 귀하의 개인정보가 유출되지 않도록 조심하시고 게시물을 포함한 타인의 개인정보를 훼손하지 않도록 유의해 주십시오. 만약 이 같은 책임을 다하지 못하고 타인의 정보 및 존엄성을 훼손할 시에는 『정보통신망이용촉진 및 정보보호 등에 관한 법률』, 『개인정보보호법』 등에 의해 처벌받을 수 있습니다.

				제14조 (의견수렴 및 불만처리)
				1.당사는 귀하의 의견을 소중하게 생각하며, 귀하는 의문사항으로부터 언제나 성실한 답변을 받을 권리가 있습니다.
				2.당사는 귀하와의 원활한 의사소통을 위해 고객센터를 운영하고 있으며 연락처는 다음과 같습니다.
				[민원 처리 센터]
				• 전자우편 : dssg8787@naver.com
				• 전화번호 : 0507-1398-4945
				3.정확한 상담을 위하여 전화상담을 사용하지 않고 전자우편을 통한 상담을 해드리고 있습니다.
				4.전자우편을 이용한 상담은 접수 후 성실하게 답변 드리겠습니다. 다만, 근무시간 이후 또는 주말 및 공휴일에는 익일 처리하는 것을 원칙으로 합니다.
				5.기타 개인정보에 관한 상담이 필요한 경우에는 회사의 위 전자우편으로 문의하실 수 있으며, 국가기관에 신고나 상담이 필요한 경우에는 아래의 연락처에 문의하셔서 도움을 받으실 수 있습니다.
				[개인정보침해신고센터]
				• 전화 : 118
				• URL : http://privacy.kisa.or.kr/
				[정보보호마크인증위원회]
				• 전화 : 02-550-9531~2
				• URL : http://www.eprivacy.or.kr
				[대검찰청 사이버범죄수사단]
				• 전화 : 02-3480-3571
				• URL : cybercid@spo.go.kr
				[경찰청 사이버안전국]
				• 전화 : 182
				• URL : http://cyberbureau.police.go.kr/

				제15조 (개인정보관리책임자 및 담당자)
				회사는 귀하가 좋은 정보를 안전하게 이용할 수 있도록 최선을 다하고 있습니다. 개인정보를 보호하는데 있어 귀하께 고지한 사항들에 반하는 사고가 발생할 시에 개인정보관리책임자가 모든 책임을 집니다. 그러나 기술적인 보완조치를 했음에도 불구하고, 해킹 등 기본적인 네트워크상의 위험성에 의해 발생하는 예기치 못한 사고로 인한 정보의 훼손 및 방문자가 작성한 게시물에 의한 각종 분쟁에 관해서는 책임이 없습니다. 귀하의 개인정보를 취급하는 책임자 및 담당자는 다음과 같으며 개인정보 관련 문의사항에 신속하고 성실하게 답변해드리고 있습니다.
				[개인정보관리책임자]
				• 성 명 : yang
				• 전자우편 : yangcompany7@gmail.com
				• 전화번호 : 0505-1717-3333

				제16조 (아동의 개인정보보호)
				회사는 만 14세 미만 아동의 개인정보를 보호하기 위하여, 만 14세 미만 아동의 회원가입은 제한합니다.

				제17조 (고지의 의무)
				현 개인정보처리방침은 정부의 정책 또는 보안기술의 변경에 따라 내용의 추가, 삭제 및 수정이 있을 시에는 개정 최소 7일 전(단, 중요한 내용 변경의 경우 30일 전)부터 공지란, 홈페이지 또는 이메일을 통해 고지할 것입니다.
				- 공고일자: 2022년 6월 30일
				- 시행일자: 2022년 6월 30일
			</div>
			<div class="modal-footer">
				<div class="left-side">
					<button type="button" class="btn btn-default btn-simple" data-dismiss="modal">Never mind</button>
				</div>
				<div class="divider"></div>
				<div class="right-side">
					<button type="button" class="btn btn-primary btn-simple agree-confirm">동의</button>
					<button type="button" class="btn btn-secondary btn-simple agree-close">닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>
<%--모달끝--%>


<%-- Login content --%>
<div id="enroll-container" class="mx-auto text-center mt-5 mb-5">
	<form:form name="memberCreateFrm" action="" method="POST">
		<h1>Sign up</h1>
				<div class="form-group">
					<label class="form-label mt-4" for="username">아이디</label>
					<input type="text" value="" class="form-control" id="username" name="username" placeholder="4~10글자 영문자, 숫자 조합">
					<div class="valid-feedback" id ="usernameValid">사용가능한 아이디입니다.</div>
					<div class="invalid-feedback" id="usernameInvalid">4글자 이상 10글자 이하의 영문자, 숫자 조합으로 작성해주세요.</div>
					<div class="invalid-feedback" id="usernameTakenFeedback">이미 사용 중인 아이디입니다.</div>
				</div>
				<div class="form-group">
					<label class="form-label mt-4" for="password">비밀번호</label>
					<input type="password" value="" class="form-control" id="password" name="password" placeholder="6~12글자의 영소문자+ 숫자 + 특수문자(!,@,#)포함">
					<div class="valid-feedback" id="pwdValid">입력하신 비밀번호는 사용 가능합니다.</div>
					<div class="invalid-feedback" id="pwdInvalid">비밀번호는  6~12자, 1개 이상의 숫자와 특수문자 ! @ # 중 하나를 포함해야합니다.</div>

					<label class="form-label mt-4" for="pwdConfirmation">비밀번호 확인</label>
					<input type="password" value="" class="form-control" id="pwdConfirmation">
					<div class="valid-feedback" id="pwdCfmValid">비밀번호 확인 되었습니다.</div>
					<div class="invalid-feedback" id="pwdCfmInvalid">비밀번호가 일치하지 않습니다.</div>
				</div>

				<div class="form-group">
					<label class="form-label mt-4" for="name">이름</label>
					<input type="text" value="" class="form-control" id="name" name="name">
					<div class="invalid-feedback" id="nameInvalid">이름을 입력해주세요</div>
				</div>

				<div class="form-group">
					<label class="form-label mt-4" for="nickname">별명</label>
					<input type="text" value="" class="form-control" id="nickname" name="nickname">
					<div class="valid-feedback" id="nicknameValid" >사용가능한 별명입니다.</div>
					<div class="invalid-feedback" id="nicknameInvalid">별명은 2~6글자의 한글 또는 영문자로 입력해주세요.</div>
					<div class="invalid-feedback" id="nicknameChkInvalid">이미 존재하는 별명입니다.</div>
				</div>
				<div>
					<label class="form-label mt-4">성별</label>
					<div class="form-check">
						<input class="form-check-input" id="M" type="radio" name="gender" value="M" checked/>
						<label class="form-check-label" for="M">🧑 Male</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="gender" id="F" value="F"/>
						<label class="form-check-label" for="F">👩 Female</label>
					</div>
				</div>
				<div class="form-group">
					<label class="form-label mt-4" for="phone">연락처</label>
					<input type="text" value="" class="form-control" id="phone" name="phone">
					<div class="valid-feedback" id="phoneValid" >사용가능한 번호입니다.</div>
					<div class="invalid-feedback" id="phoneInvalid">'-'제외하고 숫자 11자리로 입력해주세요</div>
					<div class="invalid-feedback" id="phoneChkInvalid">이미 존재하는 번호입니다. 다시 확인해주세요.</div>
				</div>
				<div class="form-group email-form">
					<label for="email">이메일</label>
						<div class="d-flex flex-row">
							<input type="text" class="form-control" id="email" name="email" style="width:250px; margin-right:5px;">
							<div style="width:250px;">
								<select class="form-control" name="emailDomain" id="emailDomain" >
									<option>메일주소 선택</option>
									<option>@naver.com</option>
									<option>@daum.net</option>
									<option>@gmail.com</option>
									<option>@hanmail.com</option>
								</select>
							</div>
						</div>
						<div class="input-group-addon d-flex justify-content-end">
							<button type="button" class="btn btn-primary" id="mail-Check-Btn">이메일 인증</button>
						</div>
						<div class="mail-check-box mt-2">
							<input class="form-control mail-check-input" id="mailAuth" placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled" maxlength="6">
							<div class="valid-feedback" id="mailValid" >인증번호가 확인되었습니다.</div>
							<div class="invalid-feedback" id="mailInvalid">인증번호가 일치하지 않습니다. 다시 확인해주세요.</div>
						</div>
				</div>

				<div class="form-group">
					<label class="form-label mt-4" for="birthday">Birth Day</label>
					<input type="date" value="" class="form-control" id="birthday" name="birthday">
				</div>
				<div class="mb-4">
					<legend class="mt-4">약관동의</legend>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault1">
						<label class="form-check-label" for="flexCheckDefault1"><a href="#" id="openModalLink">회원약관</a>에 동의합니다.(필수)</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault2" >
						<label class="form-check-label" for="flexCheckDefault2"><a href="#" id="openModalLink2">개인정보 수집 및 이용에 대한 안내</a>에 동의합니다.(필수)</label>
					</div>
				</div>
			<button class = "btn btn-primary" type="submit" value="가입" style="width: 70px; height :35px;">가입</button>
			<button class = "btn btn-primary" type="reset" value="취소" style="width: 70px; height :35px;">취소</button>
	</form:form>
</div>
<script>
	// 아이디 유효성 검사
	const $validFeedback = $(".valid-feedback");
	const $invalidFeedback = $(".invalid-feedback");
	const $usernameValid = $("#usernameValid");
	const usernameInvalid = document.querySelector("#usernameInvalid");
	const usernameTakenFeedback = document.querySelector("#usernameTakenFeedback");
	const $username = $("#username");

	$username.on("input", function(){

		const username = $(this).val();

		if(!/^[a-zA-Z0-9]{4,10}$/.test(username)){
			$(this).addClass('is-invalid');
			$(this).removeClass('is-valid');
			usernameInvalid.style.display = "block";
			usernameTakenFeedback.style.display= "none";
			if(username.trim() === ''){
				usernameInvalid.style.display = "none";
				usernameTakenFeedback.style.display= "none";
				$(this).removeClass('is-invalid');
			}

		} else{

			$(this).addClass('is-valid');
			$(this).removeClass('is-invalid');
			usernameInvalid.style.display = "none";
			usernameTakenFeedback.style.display= "none";
			$usernameValid.show();

			checkIdDuplicate(username);

		}


	});

	const checkIdDuplicate = (inputUsername) => {
		$.ajax({

			url : "${pageContext.request.contextPath}/member/checkIdDuplicate.do",
			data : {
				username : inputUsername
			},
			method : "GET",
			dataType : "json",
			success(responseData){
				console.log(responseData);
				const {available, chkusername} = responseData;

				if(available === false){
					$username.removeClass("is-valid");
					$username.addClass("is-invalid");
					$usernameValid.hide();
					usernameInvalid.style.display = "none";
					usernameTakenFeedback.style.display= "block";
					if(chkusername !== inputUsername){
						$username.addClass("is-valid");
						$username.removeClass("is-invalid");
						$usernameValid.show();
						usernameInvalid.style.display = "none";
						usernameTakenFeedback.style.display= "none";
					}

				} else {
					$username.addClass("is-valid");
					$username.removeClass("is-invalid");
					$usernameValid.show();
					usernameInvalid.style.display = "none";
					usernameTakenFeedback.style.display= "none";
				}

			}
		});
	};

	// 비밀번호 유효성 검사
	const $pwdInvalid = $("#pwdInvalid");
	const $pwdValid = $("#pwdValid");
	const $pwdCfmValid = $("#pwdCfmValid");
	const $pwdCfmInvalid = $("#pwdCfmInvalid");
	const $password = $("#password");
	const $pwdConfirmation =$("#pwdConfirmation");

	$password.on("input", function(){

		const password = $(this).val();

		if(!/^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#])[a-z0-9!@#]+$/.test(password)){
			$(this).addClass('is-invalid');
			$(this).removeClass('is-valid');
			$pwdInvalid.show();
			$pwdValid.hide();
			$pwdCfmInvalid.hide();

			if(password.trim() === ''){
				$pwdInvalid.hide();
				$pwdValid.hide();
				$(this).removeClass('is-invalid');
			}

		} else{
			$(this).removeClass('is-invalid');
			$(this).addClass('is-valid');
			$pwdInvalid.hide();
			$pwdValid.show();
			$pwdCfmValid.hide();

				$pwdConfirmation.on("input", function () {
					const password = $password.val();
					const pwdConfirmation = $(this).val();

					if (password !== pwdConfirmation) {
						if (pwdConfirmation.trim() === '') {
							$pwdCfmValid.hide();
							$pwdCfmInvalid.hide();
							$(this).removeClass('is-invalid');
							$(this).removeClass('is-valid');
						} else {
							$(this).addClass('is-invalid');
							$(this).removeClass('is-valid');
							$pwdCfmValid.hide();
							$pwdCfmInvalid.show();
						}
					} else {
						$(this).removeClass('is-invalid');
						$(this).addClass('is-valid');
						$pwdCfmValid.show();
						$pwdCfmInvalid.hide();
					}
				});

		}
	});

// 이름 유효성 검사
	const $name = $("#name");
	const $nameInvalid = $("#nameInvalid");

	$name.on("input", function(){

		const name = $name.val();
		if(name.trim()===''){
			$(this).addClass('is-invalid');
			$nameInvalid.show();

			$name.on("blur", function() {
				$(this).removeClass('is-invalid');
				$(this).removeClass('is-valid');
				$nameInvalid.hide();
			});

		} else{
			$(this).addClass('is-valid');
			$(this).removeClass('is-invalid');
			$nameInvalid.hide();

			$name.on("blur", function() {
				$(this).removeClass('is-invalid');
				$(this).addClass('is-valid');
				$nameInvalid.hide();
			});
		}
	});


	//별명 유효성 검사

	const $nickname = $("#nickname");
	const $nicknameValid = $("#nicknameValid");
	const $nicknameInvalid = $("#nicknameInvalid");
	const $nicknameChkInvalid = $("#nicknameChkInvalid");

	$nickname.on("input", function(){
		const nickname = $(this).val();

		if(!/^[a-zA-Z가-힣]{2,6}$/.test(nickname)){

			$(this).addClass('is-invalid');
			$(this).removeClass('is-valid');
			$nicknameInvalid.show();
			$nicknameChkInvalid.hide();

			if(nickname.trim() === ''){
				$nicknameChkInvalid.hide();
				$nicknameInvalid.hide();
				$(this).removeClass('is-invalid');
			}

		} else {

			$(this).addClass('is-valid');
			$(this).removeClass('is-invalid');
			$nicknameValid.show();
			$nicknameInvalid.hide();
			$nicknameChkInvalid.hide();

			checkNicknameDuplicate(nickname);

		}

	});

	const checkNicknameDuplicate = (inputNickname) =>{

		console.log(inputNickname);
		$.ajax({
			url : "${pageContext.request.contextPath}/member/checkNicknameDuplicate.do",
			data : {
				nickname : inputNickname
			},
			method : "GET",
			dataType : "json",
			success(responseData){
				console.log(responseData);
				const {available} =responseData;

				if(available === false){
					$nickname.removeClass('is-valid');
					$nickname.addClass('is-invalid');
					$nicknameValid.hide();
					$nicknameInvalid.hide();
					$nicknameChkInvalid.show();

				} else{
					$nickname.addClass('is-valid');
					$nickname.removeClass('is-invalid');
					$nicknameValid.show();
					$nicknameInvalid.hide();
					$nicknameChkInvalid.hide();

				}

			}

		});


	};

	// 휴대전화 유효성 검사
	const $phone = $("#phone");
	const $phoneValid = $("#phoneValid");
	const $phoneInvalid = $("#phoneInvalid");
	const $phoneChkInvalid = $("#phoneChkInvalid");

	$phone.on("input", function(){

		const phone = $phone.val();
		if(!/^\d{11}$/.test(phone)){

			$phone.addClass('is-invalid');
			$phone.removeClass('is-valid');
			$phoneValid.hide();
			$phoneInvalid.show();
			$phoneChkInvalid.hide();

			if(phone.trim() === ''){
				$phone.removeClass('is-invalid');
				$phone.removeClass('is-valid');
				$phoneValid.hide();
				$phoneInvalid.hide();
				$phoneChkInvalid.hide();
			}

		} else{

			$phone.removeClass('is-invalid');
			$phone.addClass('is-valid');
			$phoneValid.show();
			$phoneInvalid.hide();
			$phoneChkInvalid.hide();

			checkPhoneDuplicate(phone);



		}

	});

	const checkPhoneDuplicate = (inputPhone) =>{
		console.log(inputPhone);

		$.ajax({
			url : "${pageContext.request.contextPath}/member/checkPhoneDuplicate.do",
			data : {
				phone : inputPhone
			},
			method : "GET",
			dataType : "json",
			success(responseData) {
				console.log(responseData);
				const {available} = responseData;

				if(available === false){
					$phone.addClass('is-invalid');
					$phone.removeClass('is-valid');
					$phoneValid.hide();
					$phoneInvalid.hide();
					$phoneChkInvalid.show();
				} else{
					$phone.removeClass('is-invalid');
					$phone.addClass('is-valid');
					$phoneValid.show();
					$phoneInvalid.hide();
					$phoneChkInvalid.hide();

				}
			}
		})
	};

	let code;
	// 이메일 인증
	$('#mail-Check-Btn').click(function() {
		const email = $('#email').val();// 이메일 주소값 얻어오기!
		console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인DM
		const checkInput = $('.mail-check-input') // 인증번호 입력하는곳

		$.ajax({

			url : "${pageContext.request.contextPath}/member/checkEmail.do",
			data : {
				email : email
			},
			method : "GET",
			dataType :"json",
			success(responseData){
				console.log(responseData);
				const {emailAuth} = responseData;
				checkInput.attr('disabled',false);
				code = emailAuth;
				alert('인증번호가 전송되었습니다.');
			}
		}); // end ajax
	}); // end send eamil

	//인증번호 비교
	const $mailValid = $("#mailValid");
	const $mailInvalid = $("#mailInvalid");
	const $mailAuth = $("#mailAuth");
	const $emailDomain = $('#emailDomain');
	$mailAuth.blur(function(){
		const inputCode = $(this).val();

		if(inputCode !== code){
			$mailAuth.addClass('is-invalid');
			$mailAuth.removeClass('is-valid');
			$mailValid.hide();
			$mailInvalid.show();
		} else {
			$mailAuth.removeClass('is-invalid');
			$mailAuth.addClass('is-valid');
			$mailValid.show();
			$mailInvalid.hide();
			$('#mail-Check-Btn').attr('disabled',true);
			$('#email').attr('readonly',true);
			$emailDomain.attr('readonly',true);
			$emailDomain.attr('onFocus', 'this.initialSelect = this.selectedIndex');
			$emailDomain.attr('onChange', 'this.selectedIndex = this.initialSelect');

		}

	});

// 이메일에 도메인 합치기
	$(document).ready(function() {
		const $email = $("#email");
		const $emailDomain = $("#emailDomain");

		$emailDomain.on("change", function() {
			const selectedDomain = $(this).val();
			const currentValue = $email.val();

			// 선택한 도메인이 없으면 무시
			if (!selectedDomain) {
				return;
			}

			// 첫 번째 옵션 선택 시, 입력값을 초기화
			if (selectedDomain === "메일주소 선택") {
				$email.val("");
				return;
			}

			// 입력값이 없거나 이미 선택한 도메인이 포함되어 있다면 새로운 값으로 업데이트
			if (!currentValue || currentValue.endsWith(selectedDomain)) {
				$email.val("");
				$email.val(selectedDomain);
			} else {
				// 입력값이 있고, 선택한 도메인이 포함되어 있지 않다면 도메인을 붙여서 업데이트
				if(currentValue.includes("@")){
					$email.val("");
					$email.val(selectedDomain);
				} else{
					$email.val(currentValue + selectedDomain);
				}

			}
		});
	});


	document.memberCreateFrm.onsubmit = (e) => {

	// 아이디 유효성 검사
	if (!/^[a-zA-Z0-9]{4,10}$/.test($("#username").val())) {
		// 오류 처리: 아이디 유효성 검사 실패
		alert("올바른 아이디를 입력해주세요.");
		return false;
	}

	// 비밀번호 유효성 검사
	if (!/^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#])[a-z0-9!@#]+$/.test($("#password").val())) {
		// 오류 처리: 비밀번호 유효성 검사 실패
		alert("올바른 비밀번호를 입력해주세요.");
		return false;
	}

	//비밀번호 일치여부 검사
	if($("#password").val() !== $("#pwdConfirmation").val()){
		alert("비밀번호 확인과 일치하지 않습니다. 확인해주세요.");
		return false;
	}

	// 이름 유효성 검사
	if ($("#name").val().trim() === '') {
		// 오류 처리: 이름 유효성 검사 실패
		alert("입력하신 이름을 확인해주세요.");
		return false;
	}

	// 별명 유효성 검사
	if (!/^[a-zA-Z가-힣]{2,6}$/.test($("#nickname").val())) {
		// 오류 처리: 별명 유효성 검사 실패
		alert("올바른 별명을 입력해주세요.");
		return false;
	}

	// 휴대전화 유효성 검사
	if (!/^\d{11}$/.test($("#phone").val())) {
		// 오류 처리: 휴대전화 유효성 검사 실패
		alert("입력하신 전화번호를 확인해주세요");
		return false;
	}

	if(!$("#email").val()){
		alert("이메일을 입력 후 인증을 완료해주세요.");
		return false;
	}

	if(!$("#birthday").val()){
		alert("생년월일을 입력해주세요");
		return false;
	}

	if(!$("#flexCheckDefault1").prop("checked")){
		alert("회원약관 동의를 확인 해주세요.");
		return false;
	}

	if(!$("#flexCheckDefault2").prop("checked")){
		alert("개인정보 이용 및 수집에 대한 동의를 확인 해주세요.");
		return false;
	}

	// 모든 유효성 검사 통과시에만 폼 제출
	return true;

};
</script>
<script>
<%--	회원약관 --%>
	document.addEventListener("DOMContentLoaded", function() {
		const openModalLink = document.getElementById("openModalLink");
		const modal = document.getElementById("memberAgree1");
		const checkbox = document.getElementById("flexCheckDefault1");
		openModalLink.addEventListener("click", function(event) {
			event.preventDefault(); // 기본 동작(링크 이동) 막기
			modal.style.display = "block"; // 모달 보이기
			modal.classList.add("show"); // 모달 클래스 추가 (부트스트랩 모달 스타일을 위해)
		});

		// 모달 창 외부를 클릭하면 모달이 닫히도록 설정
		modal.addEventListener("click", function(event) {
			if (event.target === modal) {
				modal.style.display = "none";
				modal.classList.remove("show");
			}
		});

		// 모달 내부의 확인 버튼 클릭 이벤트
		const agreeButton = modal.querySelector(".agree-confirm");
		agreeButton.addEventListener("click", function() {
			// 여기에 확인 버튼 클릭 시 수행할 동작 추가
			checkbox.checked = true;
			modal.style.display = "none";
			modal.classList.remove("show");
		});

		const closeButton = modal.querySelector(".agree-close");
		closeButton.addEventListener("click", function(){
			modal.classList.remove("show");
		});

	});

	// 개인정보 이용및 수집
	document.addEventListener("DOMContentLoaded", function() {
		const openModalLink = document.getElementById("openModalLink2");
		const modal = document.getElementById("memberAgree2");
		const checkbox = document.getElementById("flexCheckDefault2");
		openModalLink.addEventListener("click", function(event) {
			event.preventDefault(); // 기본 동작(링크 이동) 막기
			modal.style.display = "block"; // 모달 보이기
			modal.classList.add("show"); // 모달 클래스 추가 (부트스트랩 모달 스타일을 위해)
		});

		// 모달 창 외부를 클릭하면 모달이 닫히도록 설정
		modal.addEventListener("click", function(event) {
			if (event.target === modal) {
				modal.style.display = "none";
				modal.classList.remove("show");
			}
		});

		// 모달 내부의 확인 버튼 클릭 이벤트
		const agreeButton = modal.querySelector(".agree-confirm");
		agreeButton.addEventListener("click", function() {
			// 여기에 확인 버튼 클릭 시 수행할 동작 추가
			checkbox.checked = true;
			modal.style.display = "none";
			modal.classList.remove("show");
		});

		const closeButton = modal.querySelector(".agree-close");
		closeButton.addEventListener("click", function(){
			modal.classList.remove("show");
		});
	});



</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>