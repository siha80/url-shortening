package com.siha.homework.urlmanagement.ui;

import com.siha.homework.urlmanagement.application.UrlManagementService;
import com.siha.homework.urlmanagement.ui.event.UrlShortenEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UrlManagerController {
    @Autowired
    UrlManagementService urlManagementService;

    /**
     * url을 입력받아서 shortened url로 변환하여 반환
     */
    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public ResponseEntity convertUrlToShortened(@RequestBody UrlShortenEvent.Request request) {
        return new ResponseEntity<>(urlManagementService.toShortened(request), HttpStatus.OK);
    }

    @RequestMapping(value = "/{value}", method = RequestMethod.GET)
    public String redirectToUrl(@PathVariable String value) {
        return "redirect:" + urlManagementService.getOriginUrl(value);
    }
}
