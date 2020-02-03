package com.vodafone.eatwithrandom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.vodafone.eatwithrandom.model.TempUser;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.repository.TempUserRepository;
import com.vodafone.eatwithrandom.repository.UserRepository;
import com.vodafone.eatwithrandom.security.JwtTokenProvider;

import com.vodafone.eatwithrandom.exception.CustomException;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private TempUserRepository tempUserRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Autowired
  private EmailService emailService;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;


  public String signin(String username, String password) {
	  Optional<User> user = userRepository.findOne(username);
	  if (user.isPresent()) {
		  if (passwordEncoder.matches(password, user.get().getPassword()))
			  return jwtTokenProvider.createToken(user.get());
		  else
			  throw new CustomException("Invalid password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
	  } 
      else
    	  throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
  }

  public String signup(User user) {
    if (!userRepository.findOne(user.getUsername()).isPresent()) {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	//Check patron usuario
    	String jwt = jwtTokenProvider.createToken(user);
    	String token = tempUserRepository.saveTempUser(jwt);
    	//Send mail
    	emailService.sendEmail("Confirmación de registro", "<!DOCTYPE html>\r\n" + 
    			"<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\r\n" + 
    			"<head>\r\n" + 
    			"    <meta charset=\"utf-8\"> <!-- utf-8 works for most cases -->\r\n" + 
    			"    <meta name=\"viewport\" content=\"width=device-width\"> <!-- Forcing initial-scale shouldn't be necessary -->\r\n" + 
    			"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <!-- Use the latest (edge) version of IE rendering engine -->\r\n" + 
    			"    <meta name=\"x-apple-disable-message-reformatting\">  <!-- Disable auto-scale in iOS 10 Mail entirely -->\r\n" + 
    			"    <title></title> <!-- The title tag shows in email notifications, like Android 4.4. -->\r\n" + 
    			"\r\n" + 
    			"    <!-- Web Font / @font-face : BEGIN -->\r\n" + 
    			"    <!-- NOTE: If web fonts are not required, lines 10 - 27 can be safely removed. -->\r\n" + 
    			"\r\n" + 
    			"    <!-- Desktop Outlook chokes on web font references and defaults to Times New Roman, so we force a safe fallback font. -->\r\n" + 
    			"    <!--[if mso]>\r\n" + 
    			"        <style>\r\n" + 
    			"            * {\r\n" + 
    			"                font-family: sans-serif !important;\r\n" + 
    			"            }\r\n" + 
    			"        </style>\r\n" + 
    			"    <![endif]-->\r\n" + 
    			"\r\n" + 
    			"    <!-- All other clients get the webfont reference; some will render the font and others will silently fail to the fallbacks. More on that here: http://stylecampaign.com/blog/2015/02/webfont-support-in-email/ -->\r\n" + 
    			"    <!--[if !mso]><!-->\r\n" + 
    			"    <!-- insert web font reference, eg: <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'> -->\r\n" + 
    			"    <!--<![endif]-->\r\n" + 
    			"\r\n" + 
    			"    <!-- Web Font / @font-face : END -->\r\n" + 
    			"\r\n" + 
    			"    <!-- CSS Reset : BEGIN -->\r\n" + 
    			"    <style>\r\n" + 
    			"\r\n" + 
    			"        /* What it does: Remove spaces around the email design added by some email clients. */\r\n" + 
    			"        /* Beware: It can remove the padding / margin and add a background color to the compose a reply window. */\r\n" + 
    			"        html,\r\n" + 
    			"        body {\r\n" + 
    			"            margin: 0 auto !important;\r\n" + 
    			"            padding: 0 !important;\r\n" + 
    			"            height: 100% !important;\r\n" + 
    			"            width: 100% !important;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        /* What it does: Stops email clients resizing small text. */\r\n" + 
    			"        * {\r\n" + 
    			"            -ms-text-size-adjust: 100%;\r\n" + 
    			"            -webkit-text-size-adjust: 100%;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        /* What it does: Centers email on Android 4.4 */\r\n" + 
    			"        div[style*=\"margin: 16px 0\"] {\r\n" + 
    			"            margin: 0 !important;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        /* What it does: Stops Outlook from adding extra spacing to tables. */\r\n" + 
    			"        table,\r\n" + 
    			"        td {\r\n" + 
    			"            mso-table-lspace: 0pt !important;\r\n" + 
    			"            mso-table-rspace: 0pt !important;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        /* What it does: Fixes webkit padding issue. */\r\n" + 
    			"        table {\r\n" + 
    			"            border-spacing: 0 !important;\r\n" + 
    			"            border-collapse: collapse !important;\r\n" + 
    			"            table-layout: fixed !important;\r\n" + 
    			"            margin: 0 auto !important;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        /* What it does: Prevents Windows 10 Mail from underlining links despite inline CSS. Styles for underlined links should be inline. */\r\n" + 
    			"        a {\r\n" + 
    			"            text-decoration: none;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        /* What it does: Uses a better rendering method when resizing images in IE. */\r\n" + 
    			"        img {\r\n" + 
    			"            -ms-interpolation-mode:bicubic;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        /* What it does: A work-around for email clients meddling in triggered links. */\r\n" + 
    			"        a[x-apple-data-detectors],  /* iOS */\r\n" + 
    			"        .unstyle-auto-detected-links a,\r\n" + 
    			"        .aBn {\r\n" + 
    			"            border-bottom: 0 !important;\r\n" + 
    			"            cursor: default !important;\r\n" + 
    			"            color: inherit !important;\r\n" + 
    			"            text-decoration: none !important;\r\n" + 
    			"            font-size: inherit !important;\r\n" + 
    			"            font-family: inherit !important;\r\n" + 
    			"            font-weight: inherit !important;\r\n" + 
    			"            line-height: inherit !important;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        /* What it does: Prevents Gmail from changing the text color in conversation threads. */\r\n" + 
    			"        .im {\r\n" + 
    			"            color: inherit !important;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        /* What it does: Prevents Gmail from displaying a download button on large, non-linked images. */\r\n" + 
    			"        .a6S {\r\n" + 
    			"           display: none !important;\r\n" + 
    			"           opacity: 0.01 !important;\r\n" + 
    			"		}\r\n" + 
    			"		/* If the above doesn't work, add a .g-img class to any image in question. */\r\n" + 
    			"		img.g-img + div {\r\n" + 
    			"		   display: none !important;\r\n" + 
    			"		}\r\n" + 
    			"\r\n" + 
    			"\r\n" + 
    			"        /* What it does: Removes right gutter in Gmail iOS app: https://github.com/TedGoas/Cerberus/issues/89  */\r\n" + 
    			"        /* Create one of these media queries for each additional viewport size you'd like to fix */\r\n" + 
    			"\r\n" + 
    			"        /* iPhone 4, 4S, 5, 5S, 5C, and 5SE */\r\n" + 
    			"        @media only screen and (min-device-width: 320px) and (max-device-width: 374px) {\r\n" + 
    			"            u ~ div .email-container {\r\n" + 
    			"                min-width: 320px !important;\r\n" + 
    			"            }\r\n" + 
    			"        }\r\n" + 
    			"        /* iPhone 6, 6S, 7, 8, and X */\r\n" + 
    			"        @media only screen and (min-device-width: 375px) and (max-device-width: 413px) {\r\n" + 
    			"            u ~ div .email-container {\r\n" + 
    			"                min-width: 375px !important;\r\n" + 
    			"            }\r\n" + 
    			"        }\r\n" + 
    			"        /* iPhone 6+, 7+, and 8+ */\r\n" + 
    			"        @media only screen and (min-device-width: 414px) {\r\n" + 
    			"            u ~ div .email-container {\r\n" + 
    			"                min-width: 414px !important;\r\n" + 
    			"            }\r\n" + 
    			"        }\r\n" + 
    			"		\r\n" + 
    			"    </style>\r\n" + 
    			"\r\n" + 
    			"    <!-- What it does: Makes background images in 72ppi Outlook render at correct size. -->\r\n" + 
    			"    <!--[if gte mso 9]>\r\n" + 
    			"    <xml>\r\n" + 
    			"        <o:OfficeDocumentSettings>\r\n" + 
    			"            <o:AllowPNG/>\r\n" + 
    			"            <o:PixelsPerInch>96</o:PixelsPerInch>\r\n" + 
    			"        </o:OfficeDocumentSettings>\r\n" + 
    			"    </xml>\r\n" + 
    			"    <![endif]-->\r\n" + 
    			"\r\n" + 
    			"    <!-- CSS Reset : END -->\r\n" + 
    			"\r\n" + 
    			"    <!-- Progressive Enhancements : BEGIN -->\r\n" + 
    			"    <style>\r\n" + 
    			"\r\n" + 
    			"        /* What it does: Hover styles for buttons */\r\n" + 
    			"        .button-td,\r\n" + 
    			"        .button-a {\r\n" + 
    			"            transition: all 100ms ease-in;\r\n" + 
    			"        }\r\n" + 
    			"	    .button-td-primary:hover,\r\n" + 
    			"	    .button-a-primary:hover {\r\n" + 
    			"	        background: #555555 !important;\r\n" + 
    			"	        border-color: #555555 !important;\r\n" + 
    			"	    }\r\n" + 
    			"\r\n" + 
    			"        /* Media Queries */\r\n" + 
    			"        @media screen and (max-width: 600px) {\r\n" + 
    			"\r\n" + 
    			"            .hideName {\r\n" + 
    			"                display: none;\r\n" + 
    			"            }\r\n" + 
    			"\r\n" + 
    			"            .email-container {\r\n" + 
    			"                width: 100% !important;\r\n" + 
    			"                margin: auto !important;\r\n" + 
    			"            }\r\n" + 
    			"\r\n" + 
    			"            /* What it does: Forces table cells into full-width rows. */\r\n" + 
    			"            .stack-column {\r\n" + 
    			"                display: block !important;\r\n" + 
    			"                width: 100% !important;\r\n" + 
    			"                max-width: 100% !important;\r\n" + 
    			"                direction: ltr !important;\r\n" + 
    			"            }\r\n" + 
    			"            /* And center justify these ones. */\r\n" + 
    			"            .stack-column-center {\r\n" + 
    			"                text-align: center !important;\r\n" + 
    			"            }\r\n" + 
    			"\r\n" + 
    			"            /* What it does: Generic utility class for centering. Useful for images, buttons, and nested tables. */\r\n" + 
    			"            .center-on-narrow {\r\n" + 
    			"                text-align: center !important;\r\n" + 
    			"                display: block !important;\r\n" + 
    			"                margin-left: auto !important;\r\n" + 
    			"                margin-right: auto !important;\r\n" + 
    			"                float: none !important;\r\n" + 
    			"            }\r\n" + 
    			"            table.center-on-narrow {\r\n" + 
    			"                display: inline-block !important;\r\n" + 
    			"            }\r\n" + 
    			"\r\n" + 
    			"            /* What it does: Adjust typography on small screens to improve readability */\r\n" + 
    			"            .email-container p {\r\n" + 
    			"                font-size: 17px !important;\r\n" + 
    			"            }\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"    </style>\r\n" + 
    			"    <!-- Progressive Enhancements : END -->\r\n" + 
    			"\r\n" + 
    			"</head>\r\n" + 
    			"<!--\r\n" + 
    			"	The email background color (#222222) is defined in three places:\r\n" + 
    			"	1. body tag: for most email clients\r\n" + 
    			"	2. center tag: for Gmail and Inbox mobile apps and web versions of Gmail, GSuite, Inbox, Yahoo, AOL, Libero, Comcast, freenet, Mail.ru, Orange.fr\r\n" + 
    			"	3. mso conditional: For Windows 10 Mail\r\n" + 
    			"-->\r\n" + 
    			"<body width=\"100%\" style=\"margin: 0; padding: 0 !important; mso-line-height-rule: exactly; background-color: #ffffff;\">\r\n" + 
    			"	<center style=\"width: 100%; background-color: #ffffff;\">\r\n" + 
    			"    <!--[if mso | IE]>\r\n" + 
    			"    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"background-color: #ffffff;\">\r\n" + 
    			"    <tr>\r\n" + 
    			"    <td>\r\n" + 
    			"    <![endif]-->\r\n" + 
    			"\r\n" + 
    			"        <!-- Visually Hidden Preheader Text : BEGIN -->\r\n" + 
    			"        <div style=\"display: none; font-size: 1px; line-height: 1px; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\">\r\n" + 
    			"        </div>\r\n" + 
    			"        <!-- Visually Hidden Preheader Text : END -->\r\n" + 
    			"\r\n" + 
    			"        <!-- Create white space after the desired preview text so email clients don’t pull other distracting text into the inbox preview. Extend as necessary. -->\r\n" + 
    			"        <!-- Preview Text Spacing Hack : BEGIN -->\r\n" + 
    			"        <div style=\"display: none; font-size: 1px; line-height: 1px; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\">\r\n" + 
    			"	        &zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;\r\n" + 
    			"        </div>\r\n" + 
    			"        <!-- Preview Text Spacing Hack : END -->\r\n" + 
    			"\r\n" + 
    			"        <!-- Email Body : BEGIN -->\r\n" + 
    			"        <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"600\" style=\"margin: auto;box-shadow: 2px 2px 4px 0 rgba(0, 0, 0, 0.25);\r\n" + 
    			"        border: solid 1px #ebebeb;\" class=\"email-container\">\r\n" + 
    			"            <!-- Hero Image, Flush : BEGIN -->\r\n" + 
    			"            <tr>\r\n" + 
    			"                <td style=\"background-color: #ffffff;\">\r\n" + 
    			"                    <img src=\"http://vodafone.es/ss/Satellite?blobcol=urldata&blobkey=id&blobtable=MungoBlobs&blobwhere=1700003767679&ssbinary=true\" width=\"700\" height=\"\" alt=\"Vodafone\" border=\"0\" style=\"width: 100%; max-width: 700px; height: auto; background: #dddddd; font-family: sans-serif; font-size: 15px; line-height: 15px; color: #555555; margin: auto; display: block;\" class=\"g-img\">\r\n" + 
    			"                </td>\r\n" + 
    			"            </tr>\r\n" + 
    			"            <!-- Hero Image, Flush : END -->\r\n" + 
    			"\r\n" + 
    			"            <!-- 1 Column Text + Button : BEGIN -->\r\n" + 
    			"            <tr>\r\n" + 
    			"                <td style=\"background-color: #ffffff;\">\r\n" + 
    			"                    <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\r\n" + 
    			"                        <tr>\r\n" + 
    			"                            <td style=\"padding: 20px; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555; padding-bottom: 0px;\">\r\n" + 
    			"                                <h1 style=\"margin: 0 0 10px; font-size: 23px; line-height: 30px; color: #333333; font-weight: normal;\">Confirma tu registro</h1>\r\n" + 
    			"                                <hr style=\"color: #ebebeb; border-top: 1px solid #ebebeb;\"/>\r\n" + 
    			"                                <p style=\"margin: 10px 0 10px; font-size: 16px;\"><strong>Hola</strong></p>\r\n" + 
    			"                                <p style=\"margin: 0 0 10px; font-size: 16px;\">Estas a un paso de confirmar tu registro en eat2meat.</p>\r\n" + 
    			"								<!-- Button : BEGIN -->\r\n" + 
    			"								<table align=\"left\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"margin: auto; margin-top: 20px !important;\">\r\n" + 
    			"									<tr>\r\n" + 
    			"										<td class=\"button-td button-td-primary\" style=\"border-radius: 4px; background: #e60000;\">\r\n" + 
    			"											<a class=\"button-a button-a-primary\" href=\"https://eat2meet.herokuapp.com/eatwithrandom/postsignup/"+ token + "\" style=\"background: #e60000; border: 1px solid #e60000; font-family: sans-serif; font-size: 15px; line-height: 15px; text-decoration: none; padding: 13px 17px; color: #ffffff; display: block; border-radius: 4px;\">Confirmar registro</a>\r\n" + 
    			"										</td>\r\n" + 
    			"									</tr>\r\n" + 
    			"								</table>\r\n" + 
    			"								<!-- Button : END -->\r\n" + 
    			"                            </td>\r\n" + 
    			"                        </tr>\r\n" + 
    			"                    </table>\r\n" + 
    			"                </td>\r\n" + 
    			"            </tr>\r\n" + 
    			"            <!-- 1 Column Text + Button : END -->\r\n" + 
    			"\r\n" + 
    			"            <!-- Background Image with Text : BEGIN -->\r\n" + 
    			"            <tr>\r\n" + 
    			"                <td valign=\"middle\" style=\"text-align: left;\">\r\n" + 
    			"	                <div>\r\n" + 
    			"                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\r\n" + 
    			"                            <tr>\r\n" + 
    			"                                <td style=\"padding: 20px; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555;\">\r\n" + 
    			"                                    <hr style=\"margin: 0px; color: #ebebeb; border-top: 1px solid #ebebeb;\"/>\r\n" + 
    			"                                </td>\r\n" + 
    			"                            </tr>\r\n" + 
    			"                        </table>\r\n" + 
    			"	                </div>\r\n" + 
    			"	            </td>\r\n" + 
    			"	        </tr>\r\n" + 
    			"            <!-- Background Image with Text : END -->\r\n" + 
    			"\r\n" + 
    			"	        <!-- Clear Spacer : BEGIN -->\r\n" + 
    			"                <!-- Clear Spacer : END -->\r\n" + 
    			"            <!-- 5 Even Columns : BEGIN -->\r\n" + 
    			"\r\n" + 
    			"	        <!-- 5 Even Columns : END -->\r\n" + 
    			"\r\n" + 
    			"	        <!-- 1 Column Text : BEGIN -->\r\n" + 
    			"	        <tr>\r\n" + 
    			"	            <td style=\"background-color: #ffffff;\">\r\n" + 
    			"	                <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\r\n" + 
    			"	                    <tr>\r\n" + 
    			"                            <td style=\"padding: 20px; font-family: sans-serif; font-size: 12px; line-height: 20px; color: #555555;\">\r\n" + 
    			"                                    <p style=\"margin: 0 0 0px; font-size: 12px;\">Confidencialidad</p>\r\n" + 
    			"                                    <p style=\"margin: 0 0 10px; font-size: 12px;\">Este correo electr&oacute;nico, y en su caso, cualquier fichero anexo al mismo, contiene informaci&oacute;n de car&aacute;cter confidencial exclusivamente dirigida a su destinatario o destinatarios y propiedad de Vodafone Espa&ntilde;a. Queda prohibida su divulgaci&oacute;n, copia o distribuci&oacute;n a terceros sin la previa autorizaci&oacute;n escrita de Vodafone Espa&ntilde;a, en virtud de la legislaci&oacute;n vigente. En el caso de haber recibido este correo electr&oacute;nico por error, se ruega notificar inmediatamente esta circunstancia mediante reenv&iacute;o a la direcci&oacute;n electr&oacute;nica del remitente y destrucci&oacute;n del mismo.</p>\r\n" + 
    			"                                    <p style=\"margin: 0 0 0px; font-size: 12px;\">Confidentiality</p>\r\n" + 
    			"                                    <p style=\"margin: 0 0 10px; font-size: 12px;\">The information in this e-mail and in any attachments is classified as Vodafone Espa&ntilde;a Confidential and Porprietary Information and solely for de attention and use of the named addressee(s). You are hereby notified that any dissemination, distribution or copy of this communication is prohibited without the prior written consent of Vodafone Espa&ntilde;a and is strictly prohibited by law. If you have received this communication in error, please, notify the sender by reply e-mail.</p>\r\n" + 
    			"                            </td>\r\n" + 
    			"	                    </tr>\r\n" + 
    			"	                </table>\r\n" + 
    			"	            </td>\r\n" + 
    			"	        </tr>\r\n" + 
    			"	        <!-- 1 Column Text : END -->\r\n" + 
    			"\r\n" + 
    			"	    </table>\r\n" + 
    			"	    <!-- Email Body : END -->\r\n" + 
    			"\r\n" + 
    			"\r\n" + 
    			"\r\n" + 
    			"    <!--[if mso | IE]>\r\n" + 
    			"    </td>\r\n" + 
    			"    </tr>\r\n" + 
    			"    </table>\r\n" + 
    			"    <![endif]-->\r\n" + 
    			"    </center>\r\n" + 
    			"</body>\r\n" + 
    			"</html>", user.getUsername());
    	return token;
    } else {
    	//TODO: definir con el FRONT el formato de la excepción
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }
  
  public String postsignup(String token) {
      if (token != null) {
    	  //Recuperamos el usuario temporal
    	  Optional<TempUser> tempUser = tempUserRepository.getTempUser(token);
    	  if (tempUser.isPresent()) {
    		  //Obtenemos el usuario del jwt
              User user = jwtTokenProvider.getUser(tempUser.get().getJwt());
              //Creamos el usuario en la BD
              userRepository.saveUser(user);
              //Borramos el usuario temporal
              tempUserRepository.deleteTempUser(tempUser.get());
              return tempUser.get().getJwt(); 
    	  }
    	  else {
    		  throw new CustomException("Token dont exist", HttpStatus.UNPROCESSABLE_ENTITY);
    	  }
    	  
      } else {
          throw new CustomException("Invalid username supplied", HttpStatus.UNPROCESSABLE_ENTITY);
      }
  }

	
  /*public void delete(String name) {
    userRepository.deleteUser(name);
  }*/

  /*public User search(String name) {
    User user = userRepository.findOne(name).get();
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }*/

  /*public User whoami(HttpServletRequest req) {
    return userRepository.findOne(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req))).get();
  }*/

  /*public String refresh(String name) {
    return jwtTokenProvider.createToken(name);
  }*/

}
