package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.ModuleDao;
import uz.pdp.model.Module;

import javax.transaction.Transactional;

@Service
public class ModuleService {

    @Autowired
    ModuleDao moduleDao;

    @Transactional
    public void saveModule(Module module) {
        moduleDao.saveModule(module);
    }

    @Transactional
    public Module getModuleById(Integer moduleId) {
        return moduleDao.getModuleById(moduleId);
    }

    @Transactional
    public void deleteModule(Integer moduleId) {
        moduleDao.deleteModuleById(moduleId);
    }
}
