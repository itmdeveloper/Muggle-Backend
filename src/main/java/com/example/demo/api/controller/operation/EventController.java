package com.example.demo.api.controller.operation;

import com.example.demo.api.entity.operation.Banner;
import com.example.demo.api.entity.operation.Event;
import com.example.demo.api.service.BannerService;
import com.example.demo.api.service.EventService;
import com.example.demo.common.ApiResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.demo.utils.realIP.getClientIP;

@Api(tags = {"EVENT 관련 API"})
@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
@Slf4j
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;

    @GetMapping("")
    public ApiResponse getEvent(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        logger.info("TIME:["+start+"]\tIP:["+getClientIP(request)+"]\tRequest["+request.getRequestURI()+"]");

        List<Event> event = eventService.getEvent();

        long end = System.currentTimeMillis();
        logger.info("TIME:["+end+"]\tMS:["+(end-start)+"]\tIP:["+getClientIP(request)+"]\tRequest["+request.getRequestURI()+"]");
        return ApiResponse.success("event",event);
    }
}
