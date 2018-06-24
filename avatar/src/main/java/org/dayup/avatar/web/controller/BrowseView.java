
package org.dayup.avatar.web.controller;


import org.dayup.avatar.web.model.vo.DocumentVo;
import org.dayup.avatar.service.docs.IDocumentService;
import org.dayup.avatar.base.utils.IDSecure;
import org.dayup.avatar.base.plugins.markdown.MarkDownHtmlWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view/browse")
public class BrowseView {

    @Autowired
    private IDocumentService documentService;

    @RequestMapping("/doc/{id}")
    public ModelAndView browseDocument(@PathVariable("id") String encodeId, Model model) {
//        Long id = IDSecure.decode(encodeId);
//        DocumentVo document = documentService.getDocument(id);
//        if (document != null && !StringUtils.isEmpty(document.getContent())) {
//            document.setContent(MarkDownHtmlWrapper.ofContent(document.getContent()).toString());
//        }
//        model.addAttribute("document", document);
        return new ModelAndView("docBrowse");
    }

}
