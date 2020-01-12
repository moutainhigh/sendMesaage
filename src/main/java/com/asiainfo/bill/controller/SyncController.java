package com.asiainfo.bill.controller;

/*@Controller
@RequestMapping("syncController")
public class SyncController {
    private static Logger logger = Logger.getLogger(SyncController.class);


    @Autowired
    private SyncService syncService;

    @ResponseBody
    @RequestMapping("/syncData")
    public String saveData(HttpServletRequest req, @RequestBody String requestBody) {
        JSONObject result = new JSONObject();
        result.put("status", -1);
        try {
            syncService.syncData(requestBody, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}*/
