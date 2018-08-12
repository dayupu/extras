package org.dayup.fun.aide.web.controller.tools;

import org.dayup.fun.aide.common.utils.ASCIIUtils;
import org.dayup.fun.aide.common.utils.EncryptUtils;
import org.dayup.fun.aide.common.utils.UnicodeUtils;
import org.dayup.fun.aide.dto.tools.TextEncode;
import org.dayup.fun.aide.dto.tools.TextEncryption;
import org.dayup.fun.aide.web.config.AjaxModelAndView;
import org.dayup.fun.aide.web.config.pjax.EnableAjaxPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tools/code")
public class CodeController {

    private final static String UTF8 = "UTF-8";

    @EnableAjaxPage
    @RequestMapping("encode")
    public ModelAndView encode() {
        return new AjaxModelAndView("tools/index", "tools/content/code_encode");
    }

    @EnableAjaxPage
    @RequestMapping("hash")
    public ModelAndView hash() {
        return new AjaxModelAndView("tools/index", "tools/content/code_hash");
    }

    @EnableAjaxPage
    @RequestMapping("image")
    public ModelAndView image() {
        return new AjaxModelAndView("tools/index", "tools/content/code_image");
    }

    @ResponseBody
    @PostMapping("image/upload")
    public Map<String, Object> imageUpload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        try {
            String base64 = Base64Utils.encodeToString(file.getBytes());
            String name = file.getOriginalFilename();
            int index = name.lastIndexOf(".");
            if (index == -1) {
                throw new IllegalArgumentException("不支持的文件类型");
            }
            String imgType = name.substring(index + 1);
            result.put("success", true);
            result.put("base64", "data:image/" + imgType + ";base64," + base64);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @PostMapping("execute/encode")
    public TextEncode executeEncode(@RequestBody TextEncode text) {
        try {

            String outputText;
            switch (text.getMethod()) {
                case BASE64:
                    if (text.getOpFlag() == 1) {
                        outputText = Base64Utils.encodeToString(text.getInputText().getBytes(UTF8));
                    } else {
                        outputText = new String(Base64Utils.decodeFromString(text.getInputText()), UTF8);
                    }
                    break;
                case ASCII:
                    if (text.getOpFlag() == 1) {
                        outputText = ASCIIUtils.encode(text.getInputText());
                    } else {
                        outputText = ASCIIUtils.decode(text.getInputText());
                    }
                    break;
                case URL:
                    if (text.getOpFlag() == 1) {
                        outputText = URLEncoder.encode(text.getInputText(), UTF8);
                    } else {
                        outputText = URLDecoder.decode(text.getInputText(), UTF8);
                    }
                    break;
                case UNICODE:
                    if (text.getOpFlag() == 1) {
                        outputText = UnicodeUtils.encode(text.getInputText());
                    } else {
                        outputText = UnicodeUtils.decode(text.getInputText());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Not support SHA type");
            }
            text.setSuccess(true);
            text.setOutputText(outputText);
        } catch (Exception e) {
            text.setSuccess(false);
            text.setOutputText(null);
            text.setMessage(e.getMessage());
        }
        return text;
    }


    @ResponseBody
    @PostMapping("encryption")
    public TextEncryption encryption(@RequestBody TextEncryption text) {
        try {
            switch (text.getEncryptType()) {
                case MD5:
                    text.setCiphertext(DigestUtils.md5DigestAsHex(text.getPlaintext().getBytes(UTF8)));
                    break;
                case SHA1:
                    text.setCiphertext(EncryptUtils.SHA(text.getPlaintext(), "SHA1"));
                    break;
                case SHA256:
                    text.setCiphertext(EncryptUtils.SHA(text.getPlaintext(), "SHA-256"));
                    break;
                case SHA512:
                    text.setCiphertext(EncryptUtils.SHA(text.getPlaintext(), "SHA-512"));
                    break;
                case AES:
                    text.setCiphertext(EncryptUtils.AES(text.getPlaintext(), text.getSecretKey(), true));
                    break;
                default:
                    throw new IllegalArgumentException("Not support SHA type");
            }
        } catch (Exception e) {
            text.setSuccess(false);
            text.setCiphertext(null);
            text.setMessage(e.getMessage());
        }
        return text;
    }

    @ResponseBody
    @PostMapping("decryption")
    public TextEncryption decryption(@RequestBody TextEncryption text) {
        try {
            switch (text.getEncryptType()) {
                case AES:
                    text.setPlaintext(EncryptUtils.AES(text.getCiphertext(), text.getSecretKey(), false));
                    break;
                default:
                    throw new IllegalArgumentException("Not support the SHA method");
            }
        } catch (Exception e) {
            text.setSuccess(false);
            text.setPlaintext(null);
            text.setMessage(e.getMessage());
        }
        return text;
    }

}
