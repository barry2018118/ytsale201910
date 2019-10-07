var swfu ;

var settings = {
	flash_url : ctxStatic + "/js/swfupload/swfupload.swf",
	upload_url: ctx + '/manage/project/fileupload/voucher',
	file_post_name: "uploadfile",
	post_params: {"dir": $("#uploadPath").attr("path")},
	file_size_limit: "5 MB",
	file_types: "*.jpg;*.JPG;*.jpeg;*.JPEG;*.png;*.PNG;*.gif;*.GIF",
	file_types_description: "图片文件",
	file_upload_limit: 1,
	custom_settings: {
		progressTarget : "fsUploadProgress",
		cancelButtonId : "btnCancel"
	},
	debug: false,
			
	// Button settings
	button_image_url: ctxStatic + "/js/swfupload/images/upload_pic.png",
 	button_width: "108",
 	button_height: "36",
 	button_placeholder_id: "_uploadfile",
 	button_cursor:SWFUpload.CURSOR.HAND,
 	button_action:SWFUpload.BUTTON_ACTION.SELECT_FILE,
 	button_window_mode: SWFUpload.WINDOW_MODE.OPAQUE,
	 	
 	// The event handler functions are defined in handlers.js
 	file_queued_handler : fileQueued,
 	file_queue_error_handler : fileQueueError,
 	file_dialog_complete_handler : fileDialogComplete,
 	upload_start_handler : uploadStart,
 	upload_progress_handler : uploadProgress,
 	upload_error_handler : uploadError,
 	upload_success_handler : uploadSuccess,
 	upload_complete_handler : uploadComplete,
 	queue_complete_handler : queueComplete	// Queue plugin event
} ;
swfu = new SWFUpload(settings);


function uploadSuccess(file, serverData) {
	try {
		
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setComplete();
		progress.setStatus("上传完成.");
		progress.toggleCancel(false);

		var _data = JSON.parse(serverData) ;
		$("#imgPath").val(_data.uploadPath) ;
		$("#adPicShowImg").attr("src",picCtx+_data.uploadPath) ;
		$("#adPicShowDiv").show() ;
		$("#deleteAdPic").show() ;
		$("#imgPath_err").hide() ;
	} catch (ex) {
		this.debug(ex) ;
	}
}

function fileQueueError(file, errorCode, message) {
	var progress = new FileProgress(file, this.customSettings.progressTarget) ;
	progress.setError() ;
	progress.setStatus("上传文件格式错误，文件不能超过5MB!") ;
	progress.toggleCancel(false) ;
}