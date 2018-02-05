// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
	if (mySidebar.style.display === 'block') {
		mySidebar.style.display = 'none';
		overlayBg.style.display = "none";
	} else {
		mySidebar.style.display = 'block';
		overlayBg.style.display = "block";
	}
}

// Close the sidebar with the close button
function w3_close() {
	mySidebar.style.display = "none";
	overlayBg.style.display = "none";
}

$(document).ready(function() {
	// 勤務地IDリンクがクリックされた時
	$(document).on("click", ".commonEditLink", editHandler);
	// 勤務地IDリンクがクリックされた時のハンドル処理
	function editHandler(event) {
		event.preventDefault();
		// ロードアイコンを表示する
		// showLoader();
		var requestUrl = $(this).attr('href');
		// 対象のSequenceナンバーをリンクのhref Urlから取得する
		var seqNo = getSeqNoFromUrl(requestUrl);
		if (seqNo !== null) {
			$(".commonDetailScreen input[name='rowNo']").val(seqNo);
			$(".commonDetailScreen").submit();
		}
		return;
	}
	;
});

/**
 * リクエストURLからseqNoを取得する
 * 
 * @param requestUrl
 *            リクエストURL
 * @return sequence number param
 */
function getSeqNoFromUrl(requestUrl) {
	var seqNo = null;
	var strs = requestUrl.split('/');
	var paramUrl = strs[strs.length - 1];
	if (paramUrl.indexOf('?') > -1) {
		var params = paramUrl.split('?');
		seqNo = params[0];
	} else {
		seqNo = strs[strs.length - 1];
	}
	return seqNo;
}