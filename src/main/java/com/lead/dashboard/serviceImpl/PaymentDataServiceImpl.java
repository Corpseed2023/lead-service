package com.lead.dashboard.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.PaymentData;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.PaymentDataDto;
import com.lead.dashboard.dto.UpdateDataDto;
import com.lead.dashboard.repository.PaymentDataRepo;
import com.lead.dashboard.repository.ServiceDetailsRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.PaymentDataService;

@Service
public class PaymentDataServiceImpl implements PaymentDataService{

	@Autowired
	PaymentDataRepo paymentDataRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ServiceDetailsRepository serviceDetailsRepository;
	@Override
	public PaymentData createPaymentData(PaymentDataDto paymentDataDto) {
	    // milestone percentage are started by
		Optional<ServiceDetails> sList = serviceDetailsRepository.findById(paymentDataDto.getServiceId());

		PaymentData paymentData = new PaymentData();
		paymentData.setTransactionId(paymentDataDto.getTransactionId());
		paymentData.setProfFees(paymentDataDto.getProfFees());
		paymentData.setGovFees(paymentDataDto.getGovFees());
		paymentData.setServiceFees(paymentDataDto.getServiceFees());
		paymentData.setOtherFees(paymentDataDto.getOtherFees());
		paymentData.setTotalPayment(paymentDataDto.getTotalPayment());
		paymentData.setType(paymentDataDto.getType());
		paymentData.setMileStone(paymentDataDto.isMileStone());
		paymentData.setFully(paymentDataDto.isFully());
		paymentData.setPartial(paymentDataDto.isPartial());
		paymentData.setDocPercent(paymentDataDto.getDocPercent());
		paymentData.setFillingPercent(paymentDataDto.getFillingPercent());
		paymentData.setLiasPercent(paymentDataDto.getLiasPercent());
		paymentData.setCertPercent(100); //certificate Always 100
		paymentData.setTestPercent(paymentDataDto.getTestPercent());
		paymentDataRepo.save(paymentData);
		ServiceDetails service = sList.get();
		List<PaymentData> estimatePayment = service.getEstimatePaymentData();
		estimatePayment.add(paymentData);
		service.setEstimatePaymentData(estimatePayment);
		serviceDetailsRepository.save(service);
		return paymentData;
	}
	@Override
	public Boolean updatePaymentData(UpdateDataDto updateDataDto) {
		boolean flag=false;
		PaymentData paymentData = paymentDataRepo.findById(updateDataDto.getPaymentDataId()).get();
        User user = userRepo.findById(updateDataDto.getUserId()).get();
        List<String> rolesName = user.getUserRole().stream().map(i->i.getName()).collect(Collectors.toList());
		if(rolesName.contains("ADMIN")) {
			paymentData.setTransactionId(updateDataDto.getTransactionId());
			paymentData.setProfFees(updateDataDto.getProfFees());
			paymentData.setGovFees(updateDataDto.getGovFees());
			paymentData.setServiceFees(updateDataDto.getServiceFees());
			paymentData.setOtherFees(updateDataDto.getOtherFees());
			paymentData.setTotalPayment(updateDataDto.getTotalPayment());
			paymentData.setType(updateDataDto.getType());
			paymentData.setMileStone(updateDataDto.isMileStone());
			paymentData.setFully(updateDataDto.isFully());
			paymentData.setPartial(updateDataDto.isPartial());
			paymentData.setDocPercent(updateDataDto.getDocPercent());
			paymentData.setFillingPercent(updateDataDto.getFillingPercent());
			paymentData.setLiasPercent(updateDataDto.getLiasPercent());
			paymentData.setCertPercent(100); //certificate Always 100
			paymentData.setTestPercent(updateDataDto.getTestPercent());
			flag=true;
			paymentDataRepo.save(paymentData);

		}else {
			if(updateDataDto.getType().equalsIgnoreCase("initiated")) {
				flag=true;
				paymentData.setTransactionId(updateDataDto.getTransactionId());
				paymentData.setProfFees(updateDataDto.getProfFees());
				paymentData.setGovFees(updateDataDto.getGovFees());
				paymentData.setServiceFees(updateDataDto.getServiceFees());
				paymentData.setOtherFees(updateDataDto.getOtherFees());
				paymentData.setTotalPayment(updateDataDto.getTotalPayment());
				paymentData.setType(updateDataDto.getType());
				paymentData.setMileStone(updateDataDto.isMileStone());
				paymentData.setFully(updateDataDto.isFully());
				paymentData.setPartial(updateDataDto.isPartial());
				paymentData.setDocPercent(updateDataDto.getDocPercent());
				paymentData.setFillingPercent(updateDataDto.getFillingPercent());
				paymentData.setLiasPercent(updateDataDto.getLiasPercent());
				paymentData.setCertPercent(100); //certificate Always 100
				paymentData.setTestPercent(updateDataDto.getTestPercent());
				paymentDataRepo.save(paymentData);
			}
			
		}
		

		return flag;
	}

}