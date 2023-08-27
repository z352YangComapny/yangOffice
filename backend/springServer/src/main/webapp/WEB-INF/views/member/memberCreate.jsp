<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원등록" name="title"/>
</jsp:include>
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
				Instagram에 오신 것을 환영합니다!
				<br/>
				저희가 명시적으로 별도의 약관(본 약관이 아님)이 적용된다고 밝히지 않는 한 본 이용 약관(또는 '약관')이 회원님의 Instagram 사용에 적용되며 아래 설명된 Instagram 서비스('서비스')에 대한 정보를 제공합니다. 회원님이 Instagram 계정을 만들거나 Instagram을 이용하는 경우, 회원님은 본 약관에 동의하는 것으로 간주됩니다. Meta 서비스 약관은 이 서비스에 적용되지 않습니다.
				<br/>
				Instagram 서비스는 Meta Platforms, Inc.에서 회원님에게 제공하는 Meta 제품 중 하나입니다. 따라서 본 이용 약관은 회원님과 Meta Platforms, Inc. 사이의 계약에 해당됩니다.
				<br/>
				Instagram 서비스
				저희는 회원님에게 Instagram 서비스를 제공하는 것에 동의합니다. 서비스에는 저희가 Instagram의 사명을 실현하기 위해 제공하는 모든 Instagram 제품, 기능, 앱, 서비스, 기술, 소프트웨어가 포함됩니다. Instagram은 회원님이 사랑하는 사람들과 사항들을 더 가까이 접할 기회를 마련하는 것을 사명으로 삼습니다. 저희의 서비스는 다음과 같은 부분으로 구성됩니다.
				창작, 연결, 소통, 발견, 공유를 위한 맞춤화된 기회를 제공합니다. 사람들은 다양합니다. 따라서 저희는 회원님이 Instagram 안팎에서 창작하고, 공유하고, 인지도를 높이고, 사람들과 소통하는 데 도움이 되는 여러 가지 유형의 계정과 기능을 제공합니다. 저희는 또한 회원님이 실제로 관심을 가지는 경험을 공유함으로써 관계를 강화하길 원합니다. 따라서 저희는 회원님 및 다른 사람들이 관심 있는 사람과 사항을 파악하는 시스템을 구축하고, 파악한 정보를 이용하여 회원님이 중요한 경험을 생성하고, 발견하며, 이에 참여하고, 이를 공유하도록 돕습니다. 그 일환으로 회원님 및 다른 사람들이 Instagram 안팎에서 하는 활동에 기초하여 회원님이 관심을 가질 만한 콘텐츠, 기능, 혜택, 계정을 더 잘 보여드리고 회원님에게 Instagram을 경험하는 방법을 제안하기도 합니다.
				긍정적이고 포괄적이며 안전한 환경을 조성합니다.
				저희는 저희 커뮤니티 회원들이 도움을 필요로 한다고 생각하는 경우를 포함하여 회원들의 경험을 긍정적이고 폭넓게 만들도록 도구를 개발 및 사용하고, 자원을 제공합니다. 또한 저희는 저희 약관 및 정책의 남용 및 위반과 유해하고 사기적인 행위를 방지하기 위한 팀과 시스템을 갖추고 있습니다. 저희는 저희 플랫폼의 보안을 유지하기 위해 저희가 보유한 모든 정보(회원님의 정보 포함)를 이용합니다. 또한 저희는 오용 또는 유해한 콘텐츠에 관한 정보를 다른 Meta Companies 또는 사법당국과 공유할 수 있습니다. 개인정보처리방침에서 자세히 알아보세요.
				성장하고 있는 저희 커뮤니티에 지속적으로 서비스를 제공하는 데 도움이 되는 기술을 개발하고 사용합니다.
				성장하고 있는 저희 커뮤니티를 위해 정보를 구성하고 분석하는 것이 저희 서비스의 핵심입니다. 저희 서비스에서 큰 부분을 차지하는 것은 광범위한 글로벌 커뮤니티를 위하여 엄청나게 큰 규모로 저희 서비스를 맞춤화하고, 보호하며, 개선시키는 데 도움이 되는 첨단 기술을 개발하고 사용하는 것입니다. 인공 지능 및 머신 러닝과 같은 기술은 저희가 저희 서비스 전체에 복잡한 프로세스를 적용할 수 있게 합니다. 자동화 기술 또한 저희 서비스의 기능성과 무결성 보장에 기여합니다.
				Meta Company 제품 전반에 걸쳐 일관되고 원활한 경험을 제공합니다.
				Instagram은 Meta Companies에 포함된 제품으로서, Meta Companies는 보다 우수하고 안전하며 보안이 강화된 서비스를 제공하기 위해 기술, 시스템, 인사이트 및 정보를 공유합니다. 그 정보에는 저희가 보유하는 회원님에 대한 정보가 포함되며, 자세한 내용은 개인정보처리방침에서 알아보세요. 또한 저희는 회원님이 사용하는 여러 Meta Company 제품과 상호 작용할 수 있는 방법을 제공하며, 회원님이 Meta Company 제품 전반에서 일관적이고 원활한 경험을 얻을 수 있도록 하는 시스템을 설계했습니다.
				저희 서비스 접근에 대한 보장.
				글로벌 서비스를 운영하기 위해 저희는 회원님의 거주 국가 이외 지역을 비롯하여 전 세계에 있는 시스템에 정보를 저장하고 이전해야 합니다. 서비스를 제공하기 위해서는 본 글로벌 인프라의 사용이 필수적입니다. 해당 인프라는 Meta Platforms, Inc., Meta Platforms Ireland Limited 또는 그 계열사가 소유하거나 운영할 수 있습니다.
				회원님이 관심 있는 방식으로 회원님과 브랜드, 제품, 서비스를 연결합니다.
				저희는 회원님에게 의미 있다고 생각되는 광고, 제안, 기타 홍보 콘텐츠를 보여드리기 위해 Instagram 및 다른 Meta Company 제품의 정보 외에도 제3자의 정보를 이용합니다. 그리고 저희는 해당 콘텐츠를 회원님의 Instagram에서의 다른 경험만큼 관련성 있게 만들기 위해 노력합니다.
				연구 및 혁신.
				저희는 보유하고 있는 정보를 이용하여 저희 서비스를 연구하고, 다른 사람들과 협력하여 저희 서비스를 보다 개선하고 저희 커뮤니티의 복지에 기여하기 위한 연구를 수행합니다.

				Instagram 서비스의 재정 조달 방식
				Instagram의 사용료를 지불하지 않는 대신, 본 약관이 적용되는 서비스를 사용함으로써, 회원님은 비즈니스 및 단체들이 홍보를 위해 저희에게 비용을 지불한 광고를 Meta Company 제품 내외부에서 보게 될 수 있다는 것을 인정합니다. 저희는 회원님에게 회원님과 관련성이 높은 광고를 보여드리기 위해 회원님의 활동 및 관심사에 대한 정보 등 회원님의 개인정보를 활용합니다.
				저희는 회원님에게 회원님과 관련성 있고 유용한 광고를 노출하되, 광고주에게 회원님의 신원을 알리지는 않습니다. 저희는 회원님의 개인정보를 판매하지 않습니다. 저희는 광고주가 저희에게 사업 목표, 광고를 노출하고자 하는 대상의 유형 등을 알릴 수 있도록 허용합니다. 저희는 그 후 관심이 있을 만한 사람에게 해당 광고를 노출합니다.
				저희는 또한 사람들이 Instagram 내부와 외부에서 광고주의 콘텐츠와 어떻게 상호작용하고 있는지 광고주들이 이해할 수 있도록 광고주들에게 광고의 성과 보고서를 제공합니다. 예를 들어 광고주가 광고의 타겟을 더 잘 이해할 수 있도록 저희는 일반적 인구 통계 정보 및 관심사 정보를 광고주에게 제공할 수 있습니다. 저희는 회원님이 구체적으로 허락하지 않는 한 회원님을 직접적으로 식별할 수 있는 정보(이름, 또는 이메일 주소와 같이 그 정보만으로 회원님께 연락을 취하는 데 사용될 수 있거나 회원님을 식별할 수 있는 정보)를 공유하지 않습니다. 여기에서 Instagram 광고의 작동 방식에 대해 자세히 알아보세요.
				콘텐츠에 언급되어 있는 비즈니스 파트너와의 상업적 관계를 기반으로 제품 또는 서비스를 홍보하는 계정의 소유자가 게시한 브랜디드 콘텐츠가 Instagram에 표시될 수 있습니다. 이와 관련해서는 여기에서 자세히 알아보세요.

				개인정보처리방침
				저희 서비스를 제공하기 위해서 회원님의 정보를 수집하고 이용해야 합니다. 개인정보처리방침은 여러 Meta 제품에서 저희가 정보를 수집, 이용하고 공유하는 방법에 대해 설명합니다. 또한 Instagram 개인정보 보호 및 보안 설정을 포함해 회원님이 회원님의 정보를 관리할 수 있는 여러 방법에 대해 설명합니다. Instagram을 사용하려면 개인정보처리방침에 동의해야 합니다.

				회원님의 약속
				서비스 제공에 대한 저희의 약속에 상응하여 회원님은 다음과 같은 약속을 해주셔야 합니다.
				Instagram을 이용할 수 있는 주체. 저희는 Instagram 서비스가 가능한 개방적이고 포괄적인 서비스가 되기를 원하지만, 또한 본 서비스가 안전하고, 보안성을 갖추며, 법을 준수하는 서비스가 되기를 원합니다. 따라서 회원님이 Instagram 커뮤니티에 참여하려면 몇 가지 제한 사항을 준수해야 합니다.
				만 14세 이상이어야 합니다.
				관련 법률에 따라 Instagram 서비스를 받는 것이 일부라도 금지된 사람 또는 관련 제재 대상 명단에 있어 결제 관련 Instagram 서비스를 이용하는 것이 금지된 사람이 아니어야 합니다.
				과거에 저희가 회원님의 계정을 법률 또는 Instagram 정책 위반을 이유로 하여 비활성화한 적이 없어야 합니다.
				유죄 확정판결을 받은 성범죄자가 아니어야 합니다.
				Instagram을 사용할 수 없는 경우. 광범위한 커뮤니티에 안전하고 개방적인 Instagram 서비스가 제공되기 위해서는 우리 모두가 각자의 본분을 다해야 합니다.
				다른 사람을 사칭하거나 부정확한 정보를 제공하면 안 됩니다.
				Instagram에서 회원님의 신원을 공개할 필요는 없지만, 회원님은 저희에게 정확한 최신 정보(등록 정보 포함)를 제공해야 하며 개인정보를 제공해야 할 수도 있습니다. 또한 본인이 아닌 다른 사람이나 단체를 사칭해서는 안 되며, 다른 사람의 명시적인 허락 없이 다른 사람의 이름으로 계정을 만들 수 없습니다.
				불법적이거나, 오해의 소지가 있거나, 사기적인 행위 또는 불법적이거나 허가받지 않은 목적을 위한 어떠한 행위도 하면 안 됩니다.
				특히 회원님은 Instagram 커뮤니티 가이드라인, Meta 플랫폼 이용 약관 및 개발자 정책, 음악 가이드라인을 포함하여 본 약관 또는 저희 정책을 위반할 수 없으며, 다른 사람들이 이를 위반하도록 돕거나 조장해서도 안 됩니다.
				브랜디드 콘텐츠를 게시하는 경우 회원님은 저희의 브랜디드 콘텐츠 정책을 준수해야 하며, 이에 따라 저희의 브랜디드 콘텐츠 도구를 사용해야 합니다. 행동 또는 콘텐츠를 신고하는 방법은 고객 센터에서 알아보세요.
				회원님은 서비스의 정상적인 운영을 방해하거나 지장을 주는 어떠한 행동도 해서는 안 됩니다.
				여기에는 사기성이 짙거나 근거 없는 신고 또는 재고 요청 등 신고, 분쟁 또는 재고 요청 채널의 오용이 포함됩니다.
				허가받지 않은 방법으로 계정을 만들거나 정보에 접근하거나 정보를 수집하려 해서는 안 됩니다.
				여기에는 저희의 명시적 허락 없이 자동화된 방법으로 계정을 만들거나 정보를 수집하는 것이 포함됩니다.
				회원님은 저희 또는 저희 서비스를 통해 확보한 계정이나 정보를 판매하거나, 라이선스를 부여하거나, 구매해서는 안 됩니다.
				여기에는 회원님 계정의 일부(사용자 이름 포함) 또는 전부에 대한 구매, 판매 또는 양도가 포함되며, 다른 사용자의 로그인 정보나 배지(badge)를 요청, 수집 또는 이용하는 행위 및 Instagram 사용자 이름, 비밀번호, 부적절한 액세스 토큰에 대한 요청 또는 수집 행위가 포함됩니다.
				회원님은 다른 사람의 사생활 정보 또는 기밀 정보를 허가 없이 게시할 수 없으며 지식재산권을 포함하여 다른 사람의 권리를 침해하는 행위(예: 저작권 침해, 상표권 침해, 위조, 불법복제품)를 하여서는 안 됩니다.
				회원님은 관련법의 저작권 및 관련 권리에 관한 예외 또는 제한 사항에 따라 다른 사람의 저작물을 사용할 수 있습니다. 회원님은 콘텐츠를 게시하거나 공유하기 위한 모든 권리를 소유하고 있거나 얻었음을 진술합니다. 여기에서 회원님의 지식재산권을 침해한다고 생각되는 콘텐츠를 신고하는 방법을 포함한 자세한 내용을 알아보세요.
				회원님은 저희의 제품 또는 그 구성 요소를 수정, 변환할 수 없으며, 그 파생된 저작물을 제작하거나 역설계를 수행해서는 안 됩니다.
				저희의 사전 서면 동의 없이 회원님의 사용자 이름에 도메인 이름이나 URL을 사용하면 안 됩니다.
				회원님이 저희에게 부여한 권한. 본 계약의 일부로서, 회원님은 또한 저희가 회원님에게 서비스를 제공하기 위해 필요로 하는 권한을 저희에게 부여합니다.
				저희가 회원님의 콘텐츠에 대한 소유권을 주장하지 않는 것과는 별개로, 회원님은 저희에게 회원님 콘텐츠 사용을 허용하는 라이선스를 부여합니다.
				회원님의 콘텐츠에 대한 회원님의 권리에 있어 변경되는 사항은 없습니다. 저희는 회원님이 서비스에 또는 서비스를 통해 게시하는 콘텐츠에 대한 소유권을 주장하지 않으며 회원님은 원하는 곳 어디에서나 다른 사람들과 자유롭게 콘텐츠를 공유할 수 있습니다. 그러나 저희가 서비스를 제공하기 위해서는 회원님으로부터 특정 법적 권한('라이선스')을 부여받아야 합니다. 회원님이 저희 서비스에서 또는 저희 서비스와 관련하여 지식재산권이 적용되는 콘텐츠(사진 또는 동영상 등)를 공유, 게시 또는 업로드하는 경우, 회원님은 회원님의 콘텐츠를 호스팅, 사용, 배포, 수정, 실행, 복사, 공개적으로 수행 또는 표시, 번역 및 그 파생된 저작물을 생성할 수 있는 비독점적이고, 사용료가 없으며, 양도 가능하고, 재라이선스가 가능하며, 전 세계적인 라이선스를 저희에게 부여합니다(회원님의 개인정보처리방침 및 애플리케이션 설정과 일치함). 이러한 라이선스는 저희 서비스에 접속하고 이를 이용하는 회원님 및 다른 사람들에게 저희 서비스 이용을 가능하게 하기 위한 것입니다. 회원님은 회원님의 콘텐츠를 개별적으로 삭제하거나 계정 삭제를 통해 한 번에 모두 삭제할 수 있습니다. 저희의 정보 이용 방법과 회원님의 콘텐츠를 관리하거나 삭제하는 방법에 관한 자세한 내용을 확인하시려면, 개인정보처리방침을 살펴보시고 Instagram 고객 센터를 방문하세요.
				회원님의 사용자 이름, 프로필 사진, 계정·광고·홍보 콘텐츠에 대한 회원님의 관계 및 행동 관련 정보를 이용할 수 있는 권한.
				회원님은 저희에게 회원님의 사용자 이름, 프로필 사진 및 회원님의 활동(예: '좋아요') 또는 관계(예: '팔로우')에 관한 정보를 Meta 제품 전반에 표시되는 계정, 광고, 제안 및 회원님이 팔로우하거나 참여하는 기타 홍보 콘텐츠와 함께 또는 이와 관련하여 표시할 수 있는 권한을 무상으로 부여합니다. 예를 들어, 저희는 회원님이 Instagram에 유료로 광고를 게재하는 브랜드의 홍보 게시물에 좋아요를 눌렀다는 점을 표시할 수 있습니다. 다른 콘텐츠에서의 행동 및 다른 계정에 대한 팔로우와 마찬가지로, 홍보 콘텐츠에서의 행동 및 홍보 계정에 대한 팔로우도 해당 콘텐츠나 팔로우를 볼 수 있는 권한이 있는 사람들에게만 표시됩니다. 저희는 회원님의 광고 설정 또한 존중합니다. 여기에서 회원님의 광고 설정에 대해 자세히 알아보실 수 있습니다.
				회원님은 저희가 회원님의 기기에서 서비스 업데이트를 다운로드하고 설치할 수 있다는 것에 동의합니다.

				저희가 보유하는 추가적인 권리
				회원님이 회원님의 계정을 위해 사용자 이름 또는 이와 유사한 식별자를 선정할 경우, 저희는 적절하거나 필요하다고 판단될 경우 이를 변경할 수 있습니다(예: 타인의 지식재산권을 침해하거나 다른 사용자를 사칭하는 경우).
				저희가 소유하고 저희 서비스 내에서 사용하도록 한 지식재산권의 적용을 받는 콘텐츠(예: 회원님이 생성하거나 공유하는 콘텐츠에 추가되는 저희가 제공한 이미지, 디자인, 동영상 또는 사운드)를 회원님이 사용하는 경우, 저희는 해당 콘텐츠에 대해 모든 권리를 보유하되 회원님 고유의 콘텐츠에 대한 권리는 보유하지 않습니다.
				회원님은 저희의 브랜드 가이드라인이나 사전 서면 허락을 통해 명백하게 허락을 받은 경우에만 저희의 지식재산권 및 상표 또는 비슷한 표시를 사용할 수 있습니다.
				회원님이 저희의 소스 코드를 수정하거나, 소스 코드에 대한 파생된 저작물을 제작하거나, 소스 코드를 역컴파일하거나 기타 방법으로 소스 코드 추출을 시도하기 위해서는 저희의 서면 허락 또는 오픈 소스 라이선스에 의거한 허락을 받아야 합니다.

				콘텐츠 삭제 및 계정 비활성화 또는 해지
				회원님이 서비스에서 공유하는 콘텐츠나 정보가 본 이용 약관, 저희 정책(저희 Instagram 커뮤니티 가이드라인 포함)에 위배된다고 판단되거나 법에 따라 삭제해야 할 경우, 저희는 이를 삭제할 수 있습니다. 저희는 저희 커뮤니티 또는 서비스를 보호하기 위해 또는 회원님이 저희에게 위험 또는 법적 문제를 일으키거나, 본 약관 또는 저희 정책(저희 Instagram 커뮤니티 가이드라인 포함)을 위반하거나, 다른 사람의 지식재산권을 반복적으로 침해하거나, 법률에 따라 허용되거나 요구되는 경우에 회원님에게 서비스 전부 또는 일부를 제공하는 것을 즉시 중단하거나 거부할 수 있습니다(회원님의 Meta 제품 및 Meta Company 제품 액세스 해지 또는 비활성화 포함). 저희에게 법적으로 불리하거나 규제적 영향을 끼치는 상황을 피하거나 줄이기 위해 필요하다고 합리적으로 판단되면, 저희는 서비스를 해지 또는 변경하거나, 서비스에서 공유된 콘텐츠 또는 정보를 삭제 또는 차단하거나, 서비스 전부 또는 일부를 제공하는 것을 중단할 수 있습니다. 회원님의 계정이 오류로 인해 해지되었다고 생각되는 경우 또는 회원님의 계정을 비활성화하거나 영구 삭제하고 싶은 경우에는 저희 고객 센터로 문의하세요. 콘텐츠 또는 계정의 삭제를 요청하는 경우 삭제 프로세스는 요청 후 30일 이내에 자동으로 시작됩니다. 삭제 프로세스가 시작된 후 콘텐츠 삭제에는 최대 90일이 걸릴 수 있습니다. 이러한 콘텐츠 삭제 프로세스가 진행되는 동안 콘텐츠는 사용자에게 표시되지 않지만 계속해서 본 이용 약관 및 개인정보처리방침의 적용을 받습니다. 콘텐츠가 삭제된 후에는 백업과 재난 복구 시스템에서 삭제하는 데 최대 90일이 더 걸릴 수 있습니다.
				다음과 같은 경우 콘텐츠는 계정 삭제 후 또는 콘텐츠 삭제 프로세스가 시작된 후 90일 이내에 삭제되지 않습니다.
				다른 사람이 이 라이선스에 따라 콘텐츠를 사용하였고 아직 삭제하지 않은 경우(이 경우 콘텐츠가 삭제될 때까지 해당 라이선스가 계속 적용됨) 또는
				저희 시스템의 기술적 제한으로 인해 90일 이내 삭제가 가능하지 않은 경우(그리고 그러한 경우 저희는 기술적으로 가능해지는 즉시 콘텐츠를 삭제함) 또는
				즉시 삭제하면 다음과 같은 저희의 조치가 제한될 수 있는 경우
				불법 활동 또는 저희 약관 및 정책 위반 사항에 대한 조사 또는 규명(예: 저희 제품 또는 시스템 오용 사례의 조사 또는 규명)
				저희 제품, 시스템, 사용자의 안전 및 보안 보호
				증거 보존 등 법적 의무의 준수 또는
				사법 또는 행정 당국, 법 집행 기관 또는 정부 기관의 요청 준수
				이 경우, 콘텐츠는 콘텐츠 유지 목적을 위해 필요한 기간 동안에만 유지됩니다(정확한 기간은 사례별로 다름).
				회원님이 계정을 삭제하거나 비활성화하면 본 약관은 회원님과 저희의 계약과 마찬가지로 해지되나, 본 조항 및 아래 조항('계약 및 상호 의견이 합치되지 않는 경우')은 계정이 해지, 비활성화 또는 삭제된 후에도 여전히 적용됩니다.

				계약 및 상호 의견이 합치되지 않는 경우
				저희와의 계약.
				서비스를 통한 회원님의 음악 사용 역시 저희 뮤직 가이드라인의 적용을 받으며, 회원님의 저희 API 사용에는 저희의 Meta 플랫폼 약관 및 개발자 정책이 적용됩니다. 회원님이 기타 특정 기능 또는 관련 서비스를 이용하는 경우, 회원님은 추가 약관에 대해 동의할 기회를 제공받으며, 추가 약관은 저희와 회원님 간의 합의 일부를 구성합니다. 예를 들어, 회원님이 결제 기능을 사용하는 경우, 회원님은 커뮤니티 결제 약관에 동의해야 합니다. 추가 약관이 본 계약과 상충될 경우 추가 약관이 우선합니다.
				본 계약의 일부가 이행 불가능한 경우에도, 나머지 부분은 계속해서 효력을 갖습니다.
				본 계약의 개정이나 면제는 저희가 작성하고 서명한 서면으로만 가능합니다. 저희가 본 계약의 일부를 이행하지 못하더라도, 이는 계약의 포기로 간주되지 않습니다.
				저희는 회원님에게 명시적으로 부여된 권한을 제외한 모든 권한을 보유합니다.
				본 계약에 따른 권리 보유자.
				본 계약은 제3자에게 권리를 부여하지 않습니다.
				회원님은 본 계약에 따른 회원님의 권리나 의무를 저희의 동의 없이 타인에게 양도할 수 없습니다.
				저희의 권리와 의무는 타인에게 양도될 수 있습니다. 이러한 양도는, 예를 들어, (인수합병이나 자산매각으로 인해) 저희의 소유권이 변경되는 경우 또는 법률에 의해 이뤄질 수 있습니다.
				유사시의 책임자.
				저희 서비스는 '있는 그대로' 제공되며, 저희는 저희의 서비스가 안전성과 보안성을 갖춘 채 제공된다거나 항상 완벽하게 작동한다고 보장할 수 없습니다. 관련 법률이 허용하는 한도 내에서 저희는 또한 상업성, 특정 목적을 위한 적합성, 권한 및 비침해성에 대한 묵시적 보증을 포함하되 이에 제한되지 않고 명시적이거나 묵시적인 어떠한 보증도 하지 않습니다.
				또한 저희는 사람들의 활동이나 말을 통제하지 않으며, 사람들(또는 회원님)의 활동이나 행동(온라인, 오프라인 불문) 또는 콘텐츠(불법 또는 유해 콘텐츠 포함)에 대해 책임지지 않습니다. 또한 저희는 다른 사람이나 기업에서 제공하는 서비스와 기능에 대해서도 책임지지 않습니다. 회원님이 저희 서비스를 통해 이러한 서비스와 기능에 접속하더라도 마찬가지입니다.
				서비스에서 발생하는 모든 사안에 대한 저희의 책임은 법률이 허용하는 범위 내에서 최대한도로 제한됩니다. 저희는 저희 서비스에 문제가 있는 때 해당 문제가 끼칠 수 있는 모든 발생 가능한 영향을 알 수 없습니다. 회원님은 본 약관으로부터 또는 본 약관과 관련하여 발생하는 수익, 수입, 정보 또는 데이터의 손실이나 파생적, 특수적, 간접적, 전형적, 징벌적 또는 부수적 손해에 대해, 저희가 그 발생 가능성을 알고 있었는지 여부를 불문하고, 저희는 책임지지 않는다는 것에 동의합니다. 여기에는 저희가 회원님의 콘텐츠, 정보 또는 계정을 삭제하는 경우도 포함됩니다.
				단, 위와 같은 규정에도 불구하고, 관련 법률에 의해 위 규정에 따른 책임 제한이나 면제가 허용되지 않을 수 있습니다. 이 경우, 저희는 해당 법률이 규정하고 있는 범위 내에서 책임을 부담합니다.
				분쟁 해결 방법.
				회원님이 소비자인 경우 또는 관련 법률이 요구하는 경우, 회원님이 거주하는 국가의 법률이 본 약관과 관련하여 발생하는 회원님의 모든 청구, 소송 사유 또는 분쟁('청구')에 적용되고, 회원님은 관할권이 있는 국가의 관할 법원에서 해당 청구를 해결할 수 있습니다. 다른 모든 경우에 회원님은 해당 청구가 미국 캘리포니아 북부 지방 법원 또는 산 마테오 카운티에 소재한 주 법원에서만 해결되고, 해당 청구의 소송 목적에 대해 이러한 법원 중 한 곳의 속인적 관할권을 따르고, 법 조항의 상충 여부와 관계없이 본 이용 약관 및 모든 청구에 캘리포니아 주법이 적용된다는 것에 동의합니다. 앞선 내용을 침해하지 않는 범위에서, 회원님이 저희 제품에 대해 악용, 방해 또는 무단 사용을 시도하는 경우 Meta Platforms Inc.는 단독 재량에 따라 회원님이 거주하며 청구의 관할권이 있는 국가에서 회원님에 대한 관련 청구를 제출할 수 있음에 회원님은 동의합니다.
				요청하지 않은 자료.
				저희는 피드백 또는 기타 제안을 언제든 환영하지만, 이를 어떠한 제한 또는 보상 의무 없이 활용할 수 있고, 이를 기밀로 유지할 의무가 없습니다.

				본 약관 개정
				저희는 Instagram 서비스 및 정책을 변경할 수 있고, Instagram 서비스 및 정책이 정확하게 반영되도록 하기 위해 본 약관을 변경해야 할 경우가 있습니다. 법적으로 달리 요구되지 않는 한 저희는 본 약관을 변경하기 전에 회원님에게 알리고, 개정된 약관의 효력이 발생되기 최소 30일 전에 회원님에게 이를 검토할 기회를 제공합니다. 변경 발효일 이후에도 회원님이 계속 Instagram 서비스를 이용하실 경우, 회원님은 개정된 약관의 적용을 받게 됩니다. 본 약관 또는 개정된 약관에 동의하지 않을 경우에는 여기서 회원님의 계정을 삭제하면 됩니다.
				수정된 날짜: 2022년 7월 26일
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

				YANG COMPANY(이하 ‘회사’)의 개인정보처리방침은 아래의 내용을 담고 있습니다.

				제1조 목적
				제2조 수집하는 개인정보의 항목
				제3조 개인정보 수집에 대한 동의
				제4조 개인정보의 수집 및 이용목적
				제5조 쿠키에 의한 개인정보 수집
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