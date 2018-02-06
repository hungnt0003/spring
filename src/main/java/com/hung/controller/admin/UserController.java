package com.hung.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hung.common.AdminModelAndView;
import com.hung.common.CommonController;
import com.hung.common.constants.UrlConstants;
import com.hung.common.constants.WordConstants;
import com.hung.common.exception.StoreException;
import com.hung.common.utils.CommonObjectUtils;
import com.hung.common.utils.ListDto;
import com.hung.common.utils.ListElementDto;
import com.hung.dto.UserDto;
import com.hung.service.IUserService;

import javassist.NotFoundException;

/**
 * クラスタイトル(ピリオド削除厳禁).
 *
 * <pre>
 * 内容, 使用例など
 * </pre>
 *
 * @author Mitsui Zosen Systems Research Inc.
 * @version X.X
 * @since TIME-3 X.X
 */
@Controller
public class UserController extends CommonController {

	// ■ SessionKey
	/** SessionKey - 一覧データ. */
	private static final String SESSION_LIST_ELEMENT_KEY = UserController.class.getSimpleName() + "LIST";
	/** SessionKey - 選択データ. */
	private static final String SESSION_ELEMENT_KEY = UserController.class.getSimpleName() + "SELECTED_ITEM";

	/** IUserService. */
	@Autowired
	IUserService userService;

	/**
	 * 
	 * 一覧画面.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = UrlConstants.URL_ADMIN_USER, method = RequestMethod.GET)
	public ModelAndView users(Model model) {

		clearSessionData(SESSION_LIST_ELEMENT_KEY, SESSION_ELEMENT_KEY);

		List<UserDto> userDtos = new ArrayList<>();
		try {
			userDtos = userService.search();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Wrap
		ListDto<UserDto> dispList = new ListDto<>(userDtos);

		setSessionData(SESSION_LIST_ELEMENT_KEY, dispList);
		model.addAttribute(LIST_ELEMENT_KEY, dispList);

		model.addAttribute(MAIN_CONTENT_VIEW_KEY, "screens/users/user_list");

		return new AdminModelAndView();

	}

	/**
	 * 
	 * 追加.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = UrlConstants.URL_ADMIN_USER_ADD, method = RequestMethod.GET)
	public ModelAndView add(Model model) {
		clearSessionData(SESSION_ELEMENT_KEY);
		if (!model.containsAttribute(ELEMENT_KEY)) {
			UserDto user = new UserDto();
			// Set ViewInfo
			model.addAttribute(ELEMENT_KEY, user);
		}
		model.addAttribute(MAIN_CONTENT_VIEW_KEY, "screens/users/user_edit");

		return new AdminModelAndView();
	}

	/**
	 * 
	 * 編集.
	 * 
	 * @param model
	 * @param redirectAttributes
	 * @param elementDto
	 * @return
	 */
	@RequestMapping(value = UrlConstants.URL_ADMIN_USER_EDIT, method = RequestMethod.GET)
	public ModelAndView edit(Model model, final RedirectAttributes redirectAttributes,
			@ModelAttribute("elementDto") ListElementDto<UserDto> elementDto) {

		AdminModelAndView mv = new AdminModelAndView();

		// Get userDto from Session
		UserDto userDtoSession = (UserDto) getSessionData(SESSION_ELEMENT_KEY);

		if (CommonObjectUtils.isNullOrEmpty(userDtoSession)) {
			// 選択された値の取得
			ListDto<UserDto> list = (ListDto<UserDto>) getSessionData(SESSION_LIST_ELEMENT_KEY);
			if (CommonObjectUtils.isNotNullOrEmpty(list)) {
				try {
					userDtoSession = list.getElement(elementDto);
				} catch (NotFoundException e) {
					handleException(redirectAttributes, REDIRECT + UrlConstants.URL_ADMIN_USER,
							WordConstants.WRD_MESS_ERR_NOT_DATA);
				}
			}
		}

		// Session又は一覧画面からのアクセスではない場合、エラーとみなされ、一覧画面に戻る
		if (CommonObjectUtils.isNullOrEmpty(userDtoSession)) {
			handleException(redirectAttributes, REDIRECT + UrlConstants.URL_ADMIN_USER,
					WordConstants.WRD_MESS_ERR_NOT_DATA);
		}

		// 再取得
		UserDto userDto;
		try {
			if (!model.containsAttribute(ELEMENT_KEY)) {
				userDto = userService.getUser(userDtoSession.getUserName());
				// NewFlag：False
				userDto.setNewFlag(false);
				// userDto.setuserName(userDtoSession.getuserName());
				// Set Session
				setSessionData(SESSION_ELEMENT_KEY, userDto);
				// Set ViewInfo
				model.addAttribute(ELEMENT_KEY, userDto);
			}
		} catch (NotFoundException e) {
			// 見つからない場合は、一覧画面にリダイレクトする
			handleException(redirectAttributes, REDIRECT + UrlConstants.URL_ADMIN_USER,
					WordConstants.WRD_MESS_ERR_NOT_DATA);
		}
		model.addAttribute(MAIN_CONTENT_VIEW_KEY, "screens/users/user_edit");
		return mv;
	}

	@RequestMapping(value = UrlConstants.URL_ADMIN_USER_STORE, method = RequestMethod.POST)
	public ModelAndView store(Model model, final RedirectAttributes redirectAttributes,
			@ModelAttribute(ELEMENT_KEY) @Validated UserDto userDto, BindingResult bindingResult) {

		AdminModelAndView mv = new AdminModelAndView();

		// Get userDto from Session
		UserDto userDtoSession = (UserDto) getSessionData(SESSION_ELEMENT_KEY);
		if (userDtoSession != null) {
			// 編集処理
			userDtoSession.setNewFlag(false);
			// Sessionの値で同期
			// userDto.setuserId(userDtoSession.getuserId());
			// userDto.setStDate(userDtoSession.getStDate());
			// userDto.setEdDate(userDtoSession.getEdDate());
		} else {
			// 新規登録処理
			userDto.setNewFlag(true);

		}

		// Validatorで新規登録か、更新か判断できるため、フラグを設定する
		// クライアントから送ったデータをValidate処理を行う
		// userValidator.validate(userDto, bindingResult);

		// Check Validate Result
		// if (bindingResult.hasErrors()) {
		// redirectAttributes.addFlashAttribute(BINDING_RESULT + user_KEY,
		// bindingResult);
		// redirectAttributes.addFlashAttribute(user_KEY, userDto);
		//
		// if (userDto.isNewFlag()) {
		// mv.setViewName(REDIRECT + MasterUrlConstants.URL_user_ADD);
		// } else {
		// mv.setViewName(REDIRECT + MasterUrlConstants.URL_user_EDIT);
		// }
		// return mv;
		// }

		// 登録が失敗かどうか
		boolean isSuccess = false;
		try {
			// 登録・更新
			userService.store(userDto);
			// Set Message：成功
			setErrorMessage(model, WordConstants.WRD_MESS_STORE_SUCCESS);
			isSuccess = true;
		} catch (StoreException e) {
			// Set Message：失敗
			setErrorMessage(model, WordConstants.WRD_MESS_STORE_UNSUCCESS);
		} catch (Exception e) {
			// Set Message：その他エラー
			setErrorMessage(model, WordConstants.WRD_MESS_FATAL_ERR);
		}

		// 初期化
		if (userDto.isNewFlag()) {
			if (isSuccess) {
				// 初期化
				userDto.init();
				userDto.setNewFlag(true);
			}
			mv.setViewName(REDIRECT + UrlConstants.URL_ADMIN_USER_ADD);
		} else {
			mv.setViewName(REDIRECT + UrlConstants.URL_ADMIN_USER_EDIT);
		}
		setSessionData(SESSION_ELEMENT_KEY, userDto);
		// 成功か失敗か、どっちも再表示を行う
		redirectAttributes.addFlashAttribute(ELEMENT_KEY, userDto);
		return mv;
	}
}
