package com.example.demo.api.controller.operation;

import com.example.demo.api.entity.operation.Banner;
import com.example.demo.api.repository.operation.BannerRepository;
import com.example.demo.api.service.BannerService;
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

import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.utils.realIP.getClientIP;


@Api(tags = {"BANNER 관련 API"})
@RestController
@RequestMapping("/api/banner")
@RequiredArgsConstructor
@Slf4j
public class BannerController {

    private static final Logger logger = LoggerFactory.getLogger(BannerController.class);

    private final BannerService bannerService;

    @GetMapping("")
    public ApiResponse getBanner(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        logger.info("TIME:["+start+"]\tIP:["+getClientIP(request)+"]\tRequest["+request.getRequestURI()+"]");

        List<Banner> banner = bannerService.getBanner();

        long end = System.currentTimeMillis();
        logger.info("TIME:["+end+"]\tMS:["+(end-start)+"]\tIP:["+getClientIP(request)+"]\tRequest["+request.getRequestURI()+"]");
        return ApiResponse.success("banner",banner);
    }
}
