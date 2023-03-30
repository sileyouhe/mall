package com.sileyouhe.mall.controller;


import com.sileyouhe.mall.common.api.CommonPage;
import com.sileyouhe.mall.common.api.CommonResult;
import com.sileyouhe.mall.mbg.model.PmsProduct;
import com.sileyouhe.mall.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "PmsProductController")
@Tag(name = "PmsProductController", description = "Product management")
@Controller
@RequestMapping("/Product")
public class PmsProductController {

    @Autowired
    private PmsProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductController.class);

    @ApiOperation("List all Products")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> listAllProduct(){
        return CommonResult.success(productService.listAllProduct());
    }

    @ApiOperation("List Products by page")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> listProductByPage(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                                  @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize){
        List<PmsProduct> ProductList = productService.listProduct(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(ProductList));

    }

    @ApiOperation("Search by product name or product_sn")
    @RequestMapping(value = "/simpleList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> listByKeyword(String keyword) {
        List<PmsProduct> productList = productService.listByKeyword(keyword);
        return CommonResult.success(productList);
    }

    @ApiOperation("get Product by ID")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProduct> getProductById(@PathVariable("id") Long id){
        return CommonResult.success(productService.getProduct(id));
    }


    @ApiOperation("create new Product")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createProduct(@RequestBody PmsProduct pmsProduct){
        CommonResult commonResult;
        int count = productService.createProduct((pmsProduct));
        if (count == 1){
            commonResult = CommonResult.success(pmsProduct);
            LOGGER.debug("createProduct success:{}",pmsProduct);
        } else {
            commonResult = CommonResult.failed("create failed");
            LOGGER.debug("createProduct failed:{}",pmsProduct);
        }
        return commonResult;
    }

    @ApiOperation("update Product's information by id")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateProduct(@PathVariable("id") Long id, @RequestBody PmsProduct pmsProductDto, BindingResult result){

        CommonResult commonResult;
        int count = productService.updateProduct(id,pmsProductDto);
        if (count == 1){
            commonResult = CommonResult.success(pmsProductDto);
            LOGGER.debug("updateProduct success:{}",pmsProductDto);
        } else {
            commonResult = CommonResult.failed("update failed");
            LOGGER.debug("updateProduct failed:{}",pmsProductDto);
        }
        return commonResult;
    }

    @ApiOperation("delete Product by ID")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteProduct(@PathVariable("id") Long id){
        int count = productService.deleteProduct(id);
        if (count == 1){
            LOGGER.debug("deleteProduct success: id = {}",id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteProduct failed : id = {}", id);
            return CommonResult.failed("deleteProduct failed");
        }

    }





}
