package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.OptionDao;
import uz.pdp.model.Option;

import javax.transaction.Transactional;

@Service
public class OptionService {
    @Autowired
    OptionDao optionDao;

    @Transactional
    public Option saveOption(Option option) {
     return optionDao.saveOption(option);
    }

    @Transactional
    public Option getOptionById(Integer optionId) {
        return optionDao.getOptionById(optionId);
    }

    @Transactional
    public void deleteOption(Integer optionId) {
        optionDao.deleteOption(optionId);
    }
}
