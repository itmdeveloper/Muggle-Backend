package com.example.demo.api.service;

import com.example.demo.api.entity.operation.Banner;
import com.example.demo.api.repository.operation.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    public List<Banner> getBanner() {return bannerRepository.findAll();}

}
