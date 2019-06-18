package com.ls.adsponsor.service;

import com.alibaba.fastjson.JSON;
import com.ls.adcommon.dump.DConstant;
import com.ls.adcommon.dump.table.*;
import com.ls.adsponsor.AdSponsorApplicationTests;
import com.ls.adsponsor.constant.CommonStatus;
import com.ls.adsponsor.entity.AdPlan;
import com.ls.adsponsor.entity.AdUnit;
import com.ls.adsponsor.entity.Creative;
import com.ls.adsponsor.entity.unitcondition.AdUnitDistrict;
import com.ls.adsponsor.entity.unitcondition.AdUnitIt;
import com.ls.adsponsor.entity.unitcondition.AdUnitKeyword;
import com.ls.adsponsor.entity.unitcondition.CreativeUnit;
import com.ls.adsponsor.repository.AdPlanRepository;
import com.ls.adsponsor.repository.AdUnitRepository;
import com.ls.adsponsor.repository.CreativeRepository;
import com.ls.adsponsor.repository.unitcondition.AdUnitDistrictRepository;
import com.ls.adsponsor.repository.unitcondition.AdUnitItRepository;
import com.ls.adsponsor.repository.unitcondition.AdUnitKeywordRepository;
import com.ls.adsponsor.repository.unitcondition.CreativeUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijiayin
 */
@Slf4j
public class DumpDataService extends AdSponsorApplicationTests {
    
    @Autowired
    private AdPlanRepository planRepository;
    
    @Autowired
    private AdUnitRepository unitRepository;
    
    @Autowired
    private CreativeRepository creativeRepository;
    
    @Autowired
    private CreativeUnitRepository creativeUnitRepository;
    
    @Autowired
    private AdUnitDistrictRepository unitDistrictRepository;
    
    @Autowired
    private AdUnitItRepository unitItRepository;
    
    @Autowired
    private AdUnitKeywordRepository unitKeywordRepository;
    
    @Test
    public void dumpAdTableData(){
        dumpAdPlanTable(String.format("%s%s", DConstant.DATA_ROOT_DIR, 
                DConstant.AD_PLAN));
        dumpAdUnitTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,
                DConstant.AD_UNIT));
        dumpCreativeTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,
                DConstant.AD_CREATIVE));
        dumpCreativeUnitTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,
                DConstant.AD_CREATIVE_UNIT));
        dumpAdUnitDistrictTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,
                DConstant.AD_UNIT_DISTRICT));
        dumpAdUnitItTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,
                DConstant.AD_UNIT_IT));
        dumpAdUnitKeywordTable(String.format("%s%s", DConstant.DATA_ROOT_DIR,
                DConstant.AD_UNIT_KEYWORD));
    }
    
    private void dumpAdPlanTable(String filename){
        List<AdPlan> adPlans = planRepository.findAllByPlanStatus(CommonStatus.VALID.getStatus());
        if(CollectionUtils.isEmpty(adPlans)){
           return; 
        }
        List<AdPlanTable> adPlanTables = new ArrayList<>();
        adPlans.forEach(p -> adPlanTables.add(
                new AdPlanTable(
                        p.getId(),
                        p.getUserId(),
                        p.getPlanStatus(),
                        p.getStartDate(),
                        p.getEndDate()
                )
        ));
        
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdPlanTable planTable : adPlanTables){
                writer.write(JSON.toJSONString(planTable));
                writer.newLine();
            }
        }catch (IOException e){
            log.error("dumpAdPlanTable error");
        }
    }
    

    private void dumpAdUnitTable(String filename){
        List<AdUnit> adUnits = unitRepository.findAllByUnitStatus(CommonStatus.VALID.getStatus());
        if(CollectionUtils.isEmpty(adUnits)){
            return;
        }
        List<AdUnitTable> unitTables = new ArrayList<>();
        adUnits.forEach(p -> unitTables.add(
                new AdUnitTable(
                        p.getId(),
                        p.getUnitStatus(),
                        p.getPositionType(),
                        p.getPlanId()
                )
        ));
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitTable unitTable : unitTables){
                writer.write(JSON.toJSONString(unitTable));
                writer.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdUnitTable error");
        }
    }

    private void dumpCreativeTable(String filename){
        List<Creative> creatives = creativeRepository.findAll();
        if(CollectionUtils.isEmpty(creatives)){
            return;
        }
        List<AdCreativeTable> creativeTables = new ArrayList<>();
        creatives.forEach(p -> creativeTables.add(
                new AdCreativeTable(
                        p.getId(),
                        p.getName(),
                        p.getType(),
                        p.getMaterialType(),
                        p.getHeight(),
                        p.getWidth(),
                        p.getAuditStatus(),
                        p.getUrl()
                )
        ));
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdCreativeTable creativeTable : creativeTables){
                writer.write(JSON.toJSONString(creativeTable));
                writer.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdCreativeTable error");
        }
    }

    private void dumpCreativeUnitTable(String filename){
        List<CreativeUnit> creativeUnits = creativeUnitRepository.findAll();
        if(CollectionUtils.isEmpty(creativeUnits)){
            return;
        }
        List<AdCreativeUnitTable> creativeUnitTables = new ArrayList<>();
        creativeUnits.forEach(p -> creativeUnitTables.add(
                new AdCreativeUnitTable(
                        p.getId(),
                        p.getUnitId()
                )
        ));
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdCreativeUnitTable creativeUnitTable : creativeUnitTables){
                writer.write(JSON.toJSONString(creativeUnitTable));
                writer.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdCreativeUnitTable error");
        }
    }

    private void dumpAdUnitDistrictTable(String filename){
        List<AdUnitDistrict> unitDistricts = unitDistrictRepository.findAll();
        if(CollectionUtils.isEmpty(unitDistricts)){
            return;
        }
        List<AdUnitDistrictTable> unitDistrictTables = new ArrayList<>();
        unitDistricts.forEach(p -> unitDistrictTables.add(
                new AdUnitDistrictTable(
                        p.getUnitId(),
                        p.getProvince(),
                        p.getCity()
                )
        ));
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitDistrictTable unitDistrictTable : unitDistrictTables){
                writer.write(JSON.toJSONString(unitDistrictTable));
                writer.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdUnitDistrictTable error");
        }
    }

    private void dumpAdUnitItTable(String filename){
        List<AdUnitIt> unitIts = unitItRepository.findAll();
        if(CollectionUtils.isEmpty(unitIts)){
            return;
        }
        List<AdUnitItTable> unitItTables = new ArrayList<>();
        unitIts.forEach(p -> unitItTables.add(
                new AdUnitItTable(
                        p.getUnitId(),
                        p.getItTag()
                )
        ));
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitItTable unitItTable : unitItTables){
                writer.write(JSON.toJSONString(unitItTable));
                writer.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdUnitItTable error");
        }
    }

    private void dumpAdUnitKeywordTable(String filename){
        List<AdUnitKeyword> unitKeywords = unitKeywordRepository.findAll();
        if(CollectionUtils.isEmpty(unitKeywords)){
            return;
        }
        List<AdUnitKeywordTable> unitKeywordTables = new ArrayList<>();
        unitKeywords.forEach(p -> unitKeywordTables.add(
                new AdUnitKeywordTable(
                        p.getUnitId(),
                        p.getKeyword()
                )
        ));
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitKeywordTable unitKeywordTable : unitKeywordTables){
                writer.write(JSON.toJSONString(unitKeywordTable));
                writer.newLine();
            }
        } catch (IOException e) {
            log.error("dumpAdUnitKeywordTable error");
        }
    }
}
