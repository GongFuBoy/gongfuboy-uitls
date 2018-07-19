package com.github.gongfuboy.utils;

import java.util.Optional;

/**
 * Autogenerated by Dapeng-Code-Generator (1.2.2)
*
* DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
*  @generated

*
**/
public class TCrmCompanyMaterialInfoCreateOrModifyRequest {

    /**
    *

公司ID

    **/
    public int companyId ;
    public int getCompanyId(){ return this.companyId; }
    public void setCompanyId(int companyId){ this.companyId = companyId; }

    public int companyId(){ return this.companyId; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest companyId(int companyId){ this.companyId = companyId; return this; }

    /**
    *

月成交量

    **/
    public double monthlyVolume ;
    public double getMonthlyVolume(){ return this.monthlyVolume; }
    public void setMonthlyVolume(double monthlyVolume){ this.monthlyVolume = monthlyVolume; }

    public double monthlyVolume(){ return this.monthlyVolume; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest monthlyVolume(double monthlyVolume){ this.monthlyVolume = monthlyVolume; return this; }

    /**
    *

自填月成交量

    **/
    public double manualMonthlyVolume ;
    public double getManualMonthlyVolume(){ return this.manualMonthlyVolume; }
    public void setManualMonthlyVolume(double manualMonthlyVolume){ this.manualMonthlyVolume = manualMonthlyVolume; }

    public double manualMonthlyVolume(){ return this.manualMonthlyVolume; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest manualMonthlyVolume(double manualMonthlyVolume){ this.manualMonthlyVolume = manualMonthlyVolume; return this; }

    /**
    *

调查月用量

    **/
    public Optional<Double> researchMonthlyVolume = Optional.empty();
    public Optional<Double> getResearchMonthlyVolume(){ return this.researchMonthlyVolume; }
    public void setResearchMonthlyVolume(Optional<Double> researchMonthlyVolume){ this.researchMonthlyVolume = researchMonthlyVolume; }

    public Optional<Double> researchMonthlyVolume(){ return this.researchMonthlyVolume; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest researchMonthlyVolume(Optional<Double> researchMonthlyVolume){ this.researchMonthlyVolume = researchMonthlyVolume; return this; }

    /**
    *

旺季月用量吨数

    **/
    public Optional<Double> midSeasonVolume = Optional.empty();
    public Optional<Double> getMidSeasonVolume(){ return this.midSeasonVolume; }
    public void setMidSeasonVolume(Optional<Double> midSeasonVolume){ this.midSeasonVolume = midSeasonVolume; }

    public Optional<Double> midSeasonVolume(){ return this.midSeasonVolume; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest midSeasonVolume(Optional<Double> midSeasonVolume){ this.midSeasonVolume = midSeasonVolume; return this; }

    /**
    *

旺季月份

    **/
    public Optional<String> midSeasonMonths = Optional.empty();
    public Optional<String> getMidSeasonMonths(){ return this.midSeasonMonths; }
    public void setMidSeasonMonths(Optional<String> midSeasonMonths){ this.midSeasonMonths = midSeasonMonths; }

    public Optional<String> midSeasonMonths(){ return this.midSeasonMonths; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest midSeasonMonths(Optional<String> midSeasonMonths){ this.midSeasonMonths = midSeasonMonths; return this; }

    /**
    *

淡季月用量吨数

    **/
    public Optional<Double> offSeasonVolume = Optional.empty();
    public Optional<Double> getOffSeasonVolume(){ return this.offSeasonVolume; }
    public void setOffSeasonVolume(Optional<Double> offSeasonVolume){ this.offSeasonVolume = offSeasonVolume; }

    public Optional<Double> offSeasonVolume(){ return this.offSeasonVolume; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest offSeasonVolume(Optional<Double> offSeasonVolume){ this.offSeasonVolume = offSeasonVolume; return this; }

    /**
    *

淡季月份

    **/
    public Optional<String> offSeasonMonths = Optional.empty();
    public Optional<String> getOffSeasonMonths(){ return this.offSeasonMonths; }
    public void setOffSeasonMonths(Optional<String> offSeasonMonths){ this.offSeasonMonths = offSeasonMonths; }

    public Optional<String> offSeasonMonths(){ return this.offSeasonMonths; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest offSeasonMonths(Optional<String> offSeasonMonths){ this.offSeasonMonths = offSeasonMonths; return this; }

    /**
    *

用料要求, 1: 开口(open); 2: 不开口(close); 3: 透明度(transparency); 4: 光泽度(gloss); 5: 拉力(pull); 6: 粘性(viscosity); 7: 其他(other)

    **/
    public Optional<String> materialRequirements = Optional.empty();
    public Optional<String> getMaterialRequirements(){ return this.materialRequirements; }
    public void setMaterialRequirements(Optional<String> materialRequirements){ this.materialRequirements = materialRequirements; }

    public Optional<String> materialRequirements(){ return this.materialRequirements; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest materialRequirements(Optional<String> materialRequirements){ this.materialRequirements = materialRequirements; return this; }

    /**
    *

其他用料要求(以逗号分隔)

    **/
    public Optional<String> otherMaterialRequirements = Optional.empty();
    public Optional<String> getOtherMaterialRequirements(){ return this.otherMaterialRequirements; }
    public void setOtherMaterialRequirements(Optional<String> otherMaterialRequirements){ this.otherMaterialRequirements = otherMaterialRequirements; }

    public Optional<String> otherMaterialRequirements(){ return this.otherMaterialRequirements; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest otherMaterialRequirements(Optional<String> otherMaterialRequirements){ this.otherMaterialRequirements = otherMaterialRequirements; return this; }

    /**
    *

期现货, 2: 现货(spot); 1: 期货(futures)

    **/
    public Optional<Integer> isFutures = Optional.empty();
    public Optional<Integer> getIsFutures(){ return this.isFutures; }
    public void setIsFutures(Optional<Integer> isFutures){ this.isFutures = isFutures; }

    public Optional<Integer> isFutures(){ return this.isFutures; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest isFutures(Optional<Integer> isFutures){ this.isFutures = isFutures; return this; }

    /**
    *

是否囤货, 2: 否(no); 1: 是(yes)

    **/
    public Optional<Integer> isStoreGoods = Optional.empty();
    public Optional<Integer> getIsStoreGoods(){ return this.isStoreGoods; }
    public void setIsStoreGoods(Optional<Integer> isStoreGoods){ this.isStoreGoods = isStoreGoods; }

    public Optional<Integer> isStoreGoods(){ return this.isStoreGoods; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest isStoreGoods(Optional<Integer> isStoreGoods){ this.isStoreGoods = isStoreGoods; return this; }

    /**
    *

用料备注

    **/
    public Optional<String> remark = Optional.empty();
    public Optional<String> getRemark(){ return this.remark; }
    public void setRemark(Optional<String> remark){ this.remark = remark; }

    public Optional<String> remark(){ return this.remark; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest remark(Optional<String> remark){ this.remark = remark; return this; }

    /**
    *采购周期, 3: 不定期(sometimes); 1: 每周(weekly); 2: 每月(monthly)
    **/
    public Optional<Integer> purchaseTerm = Optional.empty();
    public Optional<Integer> getPurchaseTerm(){ return this.purchaseTerm; }
    public void setPurchaseTerm(Optional<Integer> purchaseTerm){ this.purchaseTerm = purchaseTerm; }

    public Optional<Integer> purchaseTerm(){ return this.purchaseTerm; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest purchaseTerm(Optional<Integer> purchaseTerm){ this.purchaseTerm = purchaseTerm; return this; }

    /**
    **(新)同行竞争对手名称,这里指我们自己的竞争对手(如找塑料)
    **/
    public Optional<String> horizontalCompetitionName = Optional.empty();
    public Optional<String> getHorizontalCompetitionName(){ return this.horizontalCompetitionName; }
    public void setHorizontalCompetitionName(Optional<String> horizontalCompetitionName){ this.horizontalCompetitionName = horizontalCompetitionName; }

    public Optional<String> horizontalCompetitionName(){ return this.horizontalCompetitionName; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest horizontalCompetitionName(Optional<String> horizontalCompetitionName){ this.horizontalCompetitionName = horizontalCompetitionName; return this; }

    /**
    *

短信接收号码

    **/
    public Optional<String> smsPhone = Optional.empty();
    public Optional<String> getSmsPhone(){ return this.smsPhone; }
    public void setSmsPhone(Optional<String> smsPhone){ this.smsPhone = smsPhone; }

    public Optional<String> smsPhone(){ return this.smsPhone; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest smsPhone(Optional<String> smsPhone){ this.smsPhone = smsPhone; return this; }

    /**
    *

报价账期基准天数

    **/
    public int priceAccountPeriod ;
    public int getPriceAccountPeriod(){ return this.priceAccountPeriod; }
    public void setPriceAccountPeriod(int priceAccountPeriod){ this.priceAccountPeriod = priceAccountPeriod; }

    public int priceAccountPeriod(){ return this.priceAccountPeriod; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest priceAccountPeriod(int priceAccountPeriod){ this.priceAccountPeriod = priceAccountPeriod; return this; }

    /**
    *

报价数量基准

    **/
    public double estimatedTonnage ;
    public double getEstimatedTonnage(){ return this.estimatedTonnage; }
    public void setEstimatedTonnage(double estimatedTonnage){ this.estimatedTonnage = estimatedTonnage; }

    public double estimatedTonnage(){ return this.estimatedTonnage; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest estimatedTonnage(double estimatedTonnage){ this.estimatedTonnage = estimatedTonnage; return this; }

    /**
    *

是否订阅短信: 1: 订阅; 2: 不订阅

    **/
    public int isSubscribe ;
    public int getIsSubscribe(){ return this.isSubscribe; }
    public void setIsSubscribe(int isSubscribe){ this.isSubscribe = isSubscribe; }

    public int isSubscribe(){ return this.isSubscribe; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest isSubscribe(int isSubscribe){ this.isSubscribe = isSubscribe; return this; }

    /**
    *

短信发送频率（多选，逗号分隔）；0: 无,1；星期一，2：星期二，3：星期三，4：星期四，5：星期五，6：星期六，7：星期日，8：工作日

    **/
    public Optional<String> sendSmsFrequency = Optional.empty();
    public Optional<String> getSendSmsFrequency(){ return this.sendSmsFrequency; }
    public void setSendSmsFrequency(Optional<String> sendSmsFrequency){ this.sendSmsFrequency = sendSmsFrequency; }

    public Optional<String> sendSmsFrequency(){ return this.sendSmsFrequency; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest sendSmsFrequency(Optional<String> sendSmsFrequency){ this.sendSmsFrequency = sendSmsFrequency; return this; }

    /**
    *总采购频率之月，m月n次里面的m
    **/
    public int totalPurchaseRateMonth ;
    public int getTotalPurchaseRateMonth(){ return this.totalPurchaseRateMonth; }
    public void setTotalPurchaseRateMonth(int totalPurchaseRateMonth){ this.totalPurchaseRateMonth = totalPurchaseRateMonth; }

    public int totalPurchaseRateMonth(){ return this.totalPurchaseRateMonth; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest totalPurchaseRateMonth(int totalPurchaseRateMonth){ this.totalPurchaseRateMonth = totalPurchaseRateMonth; return this; }

    /**
    *总采购频率之次，m月n次里面的n
    **/
    public int totalPurchaseRateTime ;
    public int getTotalPurchaseRateTime(){ return this.totalPurchaseRateTime; }
    public void setTotalPurchaseRateTime(int totalPurchaseRateTime){ this.totalPurchaseRateTime = totalPurchaseRateTime; }

    public int totalPurchaseRateTime(){ return this.totalPurchaseRateTime; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest totalPurchaseRateTime(int totalPurchaseRateTime){ this.totalPurchaseRateTime = totalPurchaseRateTime; return this; }

    /**
    *快塑采购频率之月，m月n次里面的m
    **/
    public int kuaisuPurchaseRateMonth ;
    public int getKuaisuPurchaseRateMonth(){ return this.kuaisuPurchaseRateMonth; }
    public void setKuaisuPurchaseRateMonth(int kuaisuPurchaseRateMonth){ this.kuaisuPurchaseRateMonth = kuaisuPurchaseRateMonth; }

    public int kuaisuPurchaseRateMonth(){ return this.kuaisuPurchaseRateMonth; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest kuaisuPurchaseRateMonth(int kuaisuPurchaseRateMonth){ this.kuaisuPurchaseRateMonth = kuaisuPurchaseRateMonth; return this; }

    /**
    *快塑采购频率之次，m月n次里面的n
    **/
    public int kuaisuPurchaseRateTime ;
    public int getKuaisuPurchaseRateTime(){ return this.kuaisuPurchaseRateTime; }
    public void setKuaisuPurchaseRateTime(int kuaisuPurchaseRateTime){ this.kuaisuPurchaseRateTime = kuaisuPurchaseRateTime; }

    public int kuaisuPurchaseRateTime(){ return this.kuaisuPurchaseRateTime; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest kuaisuPurchaseRateTime(int kuaisuPurchaseRateTime){ this.kuaisuPurchaseRateTime = kuaisuPurchaseRateTime; return this; }

    /**
    *采购日期，1-31内，用|隔开
    **/
    public String purchaseDateNumStr ;
    public String getPurchaseDateNumStr(){ return this.purchaseDateNumStr; }
    public void setPurchaseDateNumStr(String purchaseDateNumStr){ this.purchaseDateNumStr = purchaseDateNumStr; }

    public String purchaseDateNumStr(){ return this.purchaseDateNumStr; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest purchaseDateNumStr(String purchaseDateNumStr){ this.purchaseDateNumStr = purchaseDateNumStr; return this; }

    /**
    *
    **/
    public int companyLevel ;
    public int getCompanyLevel(){ return this.companyLevel; }
    public void setCompanyLevel(int companyLevel){ this.companyLevel = companyLevel; }

    public int companyLevel(){ return this.companyLevel; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest companyLevel(int companyLevel){ this.companyLevel = companyLevel; return this; }

    /**
    *加价幅度（涨）
    **/
    public Optional<Double> fareIncreaseRise = Optional.empty();
    public Optional<Double> getFareIncreaseRise(){ return this.fareIncreaseRise; }
    public void setFareIncreaseRise(Optional<Double> fareIncreaseRise){ this.fareIncreaseRise = fareIncreaseRise; }

    public Optional<Double> fareIncreaseRise(){ return this.fareIncreaseRise; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest fareIncreaseRise(Optional<Double> fareIncreaseRise){ this.fareIncreaseRise = fareIncreaseRise; return this; }

    /**
    *加价幅度（跌）
    **/
    public Optional<Double> fareIncreaseDown = Optional.empty();
    public Optional<Double> getFareIncreaseDown(){ return this.fareIncreaseDown; }
    public void setFareIncreaseDown(Optional<Double> fareIncreaseDown){ this.fareIncreaseDown = fareIncreaseDown; }

    public Optional<Double> fareIncreaseDown(){ return this.fareIncreaseDown; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest fareIncreaseDown(Optional<Double> fareIncreaseDown){ this.fareIncreaseDown = fareIncreaseDown; return this; }

    /**
    *加价幅度
    **/
    public Optional<Double> fareIncrease = Optional.empty();
    public Optional<Double> getFareIncrease(){ return this.fareIncrease; }
    public void setFareIncrease(Optional<Double> fareIncrease){ this.fareIncrease = fareIncrease; }

    public Optional<Double> fareIncrease(){ return this.fareIncrease; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest fareIncrease(Optional<Double> fareIncrease){ this.fareIncrease = fareIncrease; return this; }

    /**
    *

主料月用量

    **/
    public Optional<Integer> mainMatetialMonthCount = Optional.empty();
    public Optional<Integer> getMainMatetialMonthCount(){ return this.mainMatetialMonthCount; }
    public void setMainMatetialMonthCount(Optional<Integer> mainMatetialMonthCount){ this.mainMatetialMonthCount = mainMatetialMonthCount; }

    public Optional<Integer> mainMatetialMonthCount(){ return this.mainMatetialMonthCount; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest mainMatetialMonthCount(Optional<Integer> mainMatetialMonthCount){ this.mainMatetialMonthCount = mainMatetialMonthCount; return this; }

    /**
    *

辅料月用量

    **/
    public Optional<Integer> assistMatetialMonthCount = Optional.empty();
    public Optional<Integer> getAssistMatetialMonthCount(){ return this.assistMatetialMonthCount; }
    public void setAssistMatetialMonthCount(Optional<Integer> assistMatetialMonthCount){ this.assistMatetialMonthCount = assistMatetialMonthCount; }

    public Optional<Integer> assistMatetialMonthCount(){ return this.assistMatetialMonthCount; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest assistMatetialMonthCount(Optional<Integer> assistMatetialMonthCount){ this.assistMatetialMonthCount = assistMatetialMonthCount; return this; }

    /**
    *

特殊料月用量

    **/
    public Optional<Integer> specialMatetialMonthCount = Optional.empty();
    public Optional<Integer> getSpecialMatetialMonthCount(){ return this.specialMatetialMonthCount; }
    public void setSpecialMatetialMonthCount(Optional<Integer> specialMatetialMonthCount){ this.specialMatetialMonthCount = specialMatetialMonthCount; }

    public Optional<Integer> specialMatetialMonthCount(){ return this.specialMatetialMonthCount; }
    public TCrmCompanyMaterialInfoCreateOrModifyRequest specialMatetialMonthCount(Optional<Integer> specialMatetialMonthCount){ this.specialMatetialMonthCount = specialMatetialMonthCount; return this; }


public String toString(){
  StringBuilder stringBuilder = new StringBuilder("{");
    stringBuilder.append("\"").append("companyId").append("\":").append(this.companyId).append(",");
stringBuilder.append("\"").append("monthlyVolume").append("\":").append(this.monthlyVolume).append(",");
stringBuilder.append("\"").append("manualMonthlyVolume").append("\":").append(this.manualMonthlyVolume).append(",");
stringBuilder.append("\"").append("researchMonthlyVolume").append("\":").append(this.researchMonthlyVolume.isPresent()?this.researchMonthlyVolume.get():null).append(",");
stringBuilder.append("\"").append("midSeasonVolume").append("\":").append(this.midSeasonVolume.isPresent()?this.midSeasonVolume.get():null).append(",");
stringBuilder.append("\"").append("midSeasonMonths").append("\":\"").append(this.midSeasonMonths.isPresent()?this.midSeasonMonths.get():null).append("\",");
stringBuilder.append("\"").append("offSeasonVolume").append("\":").append(this.offSeasonVolume.isPresent()?this.offSeasonVolume.get():null).append(",");
stringBuilder.append("\"").append("offSeasonMonths").append("\":\"").append(this.offSeasonMonths.isPresent()?this.offSeasonMonths.get():null).append("\",");
stringBuilder.append("\"").append("materialRequirements").append("\":\"").append(this.materialRequirements.isPresent()?this.materialRequirements.get():null).append("\",");
stringBuilder.append("\"").append("otherMaterialRequirements").append("\":\"").append(this.otherMaterialRequirements.isPresent()?this.otherMaterialRequirements.get():null).append("\",");
stringBuilder.append("\"").append("isFutures").append("\":").append(this.isFutures.isPresent()?this.isFutures.get():null).append(",");
stringBuilder.append("\"").append("isStoreGoods").append("\":").append(this.isStoreGoods.isPresent()?this.isStoreGoods.get():null).append(",");
stringBuilder.append("\"").append("remark").append("\":\"").append(this.remark.isPresent()?this.remark.get():null).append("\",");
stringBuilder.append("\"").append("purchaseTerm").append("\":").append(this.purchaseTerm.isPresent()?this.purchaseTerm.get():null).append(",");
stringBuilder.append("\"").append("horizontalCompetitionName").append("\":\"").append(this.horizontalCompetitionName.isPresent()?this.horizontalCompetitionName.get():null).append("\",");
stringBuilder.append("\"").append("smsPhone").append("\":\"").append(this.smsPhone.isPresent()?this.smsPhone.get():null).append("\",");
stringBuilder.append("\"").append("priceAccountPeriod").append("\":").append(this.priceAccountPeriod).append(",");
stringBuilder.append("\"").append("estimatedTonnage").append("\":").append(this.estimatedTonnage).append(",");
stringBuilder.append("\"").append("isSubscribe").append("\":").append(this.isSubscribe).append(",");
stringBuilder.append("\"").append("sendSmsFrequency").append("\":\"").append(this.sendSmsFrequency.isPresent()?this.sendSmsFrequency.get():null).append("\",");
stringBuilder.append("\"").append("totalPurchaseRateMonth").append("\":").append(this.totalPurchaseRateMonth).append(",");
stringBuilder.append("\"").append("totalPurchaseRateTime").append("\":").append(this.totalPurchaseRateTime).append(",");
stringBuilder.append("\"").append("kuaisuPurchaseRateMonth").append("\":").append(this.kuaisuPurchaseRateMonth).append(",");
stringBuilder.append("\"").append("kuaisuPurchaseRateTime").append("\":").append(this.kuaisuPurchaseRateTime).append(",");
stringBuilder.append("\"").append("purchaseDateNumStr").append("\":\"").append(this.purchaseDateNumStr).append("\",");
stringBuilder.append("\"").append("companyLevel").append("\":").append(this.companyLevel).append(",");
stringBuilder.append("\"").append("fareIncreaseRise").append("\":").append(this.fareIncreaseRise.isPresent()?this.fareIncreaseRise.get():null).append(",");
stringBuilder.append("\"").append("fareIncreaseDown").append("\":").append(this.fareIncreaseDown.isPresent()?this.fareIncreaseDown.get():null).append(",");
stringBuilder.append("\"").append("fareIncrease").append("\":").append(this.fareIncrease.isPresent()?this.fareIncrease.get():null).append(",");
stringBuilder.append("\"").append("mainMatetialMonthCount").append("\":").append(this.mainMatetialMonthCount.isPresent()?this.mainMatetialMonthCount.get():null).append(",");
stringBuilder.append("\"").append("assistMatetialMonthCount").append("\":").append(this.assistMatetialMonthCount.isPresent()?this.assistMatetialMonthCount.get():null).append(",");
stringBuilder.append("\"").append("specialMatetialMonthCount").append("\":").append(this.specialMatetialMonthCount.isPresent()?this.specialMatetialMonthCount.get():null).append(",");

    stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
    stringBuilder.append("}");

  return stringBuilder.toString();
}
}
      