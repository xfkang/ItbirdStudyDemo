package com.itbird.design.chain.upgrade;

/**
 * Created by itbird on 2022/3/1
 */
public class UpgradeRequest {
    /**
     * 升级包名称
     */
    private String name;
    /**
     * 升级包解压的根路径
     */
    private String path;
    /**
     * 升级包包含的mcu文件路径
     */
    private String mcuPath;
    /**
     * 升级包包含的A系统升级文件路径
     */
    private String aSystemPath;

    public UpgradeRequest(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMcuPath() {
        return mcuPath;
    }

    public void setMcuPath(String mcuPath) {
        this.mcuPath = mcuPath;
    }

    public String getaSystemPath() {
        return aSystemPath;
    }

    public void setaSystemPath(String aSystemPath) {
        this.aSystemPath = aSystemPath;
    }

    public String getbSystemPath() {
        return bSystemPath;
    }

    public void setbSystemPath(String bSystemPath) {
        this.bSystemPath = bSystemPath;
    }

    /**
     * 升级包包含的B系统升级文件路径
     */
    String bSystemPath;
}
