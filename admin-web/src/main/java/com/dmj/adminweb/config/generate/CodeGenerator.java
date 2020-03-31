package com.dmj.adminweb.config.generate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;
import java.util.Collections;
/**
 * @author Suian
 */
@Slf4j
public class CodeGenerator {

    private static final String MODULE = "admin-web";
    private static final String AUTHOR = "ss";

    private static final String URL                = "jdbc:mysql://localhost:3306/admin?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static final String DRIVER             = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME           = "root";
    private static final String PASSWORD           = "root";
    private static final String MODULE_PACKAGE     = "com.dmj.adminweb";
    private static final String MAPPER_XML         = "/src/main/resources/mapper";
    private static final String TEMPLATE_PATH      = "/templates/mapper.xml.vm";
    private static final String LOGIC_DELETE_FIELD = "delete_flag";

    public static void main(String[] args) {
        new AutoGenerator()
                .setDataSource(dataSourceConfig())
                .setStrategy(strategyConfig())
                .setPackageInfo(packageConfig())
                .setTemplate(templateConfig())
                .setGlobalConfig(globalConfig())
                .setCfg(injectionConfig()).execute();
    }

    /**
     * 数据源配置
     */
    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig config = new DataSourceConfig();
        config.setUrl(URL).setDriverName(DRIVER).setUsername(USERNAME).setPassword(PASSWORD);

        //默认MySQL
        config.getDbQuery();
        //默认MySQL的类型转换方式,可自定义
        config.getTypeConvert();
        return config;
    }

    /**
     * 数据库表配置
     */
    private static StrategyConfig strategyConfig() {
        StrategyConfig config = new StrategyConfig();
        //是否大写命名
        config.setCapitalMode(false);
        //是否跳过视图
        config.setSkipView(true);

        //数据库表名映射到实体的命名策略
        config.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        config.setColumnNaming(NamingStrategy.underline_to_camel);
        //表前缀
        config.setTablePrefix();
        //字段前缀
        config.setFieldPrefix();
        // 自定义继承的Entity类全称，带包名
//        config.setSuperEntityClass(SUPER_ENTITY_CLASS);
        // 自定义基础的Entity类的公共字段
        // config.setSuperEntityColumns();
        // 自定义继承的Mapper类全称，带包名
        // config.setSuperMapperClass();
        // 自定义继承的Service类全称，带包名
        // config.setSuperServiceClass();
        // 自定义继承的ServiceImpl类全称，带包名
        // config.setSuperServiceImplClass();
        // 自定义继承的Controller类全称，带包名
        // config.setSuperControllerClass();

        // 设置需要generator的表名
        config.setInclude("sys_permission","sys_role","sys_role_permission","sys_user","sys_user_role");
        // 设置不需要generator的表名
        // config.setExclude();

        // 模糊匹配表名
        // config.setLikeTable();
        // 模糊排除表名
        // config.setNotLikeTable();
        // 设置是否生成字段常量,默认false -> public static final String ID = "test_id"
        config.setEntityColumnConstant(false);
        // 是否为构建者模型,默认 false
        config.setEntityBuilderModel(true);
        // 是否为lombok模型,默认 false
        config.setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀（默认 false）
        // config.setEntityBooleanColumnRemoveIsPrefix();

        config.setRestControllerStyle(true);
        // http接口名称驼峰转连字符
        config.setControllerMappingHyphenStyle(true);
        // 是否生成实体时，生成字段注解
        config.setEntityTableFieldAnnotationEnable(true);
        // 乐观锁属性名称
        // config.setVersionFieldName();
        // 逻辑删除属性名称
        config.setLogicDeleteFieldName(LOGIC_DELETE_FIELD);
        // 表填充字段
        // config.setTableFillList();
        return config;
    }

    /**
     * 包名配置
     */
    private static PackageConfig packageConfig() {
        return new PackageConfig()
                .setParent(MODULE_PACKAGE)
                .setEntity("model")
                .setMapper("mapper");
    }

    /**
     * 模板配置,主要用于自定义模板文件,一般不需要修改,使用默认的即可
     */
    private static TemplateConfig templateConfig() {
        return new TemplateConfig();
    }

    /**
     * 全局策略配置
     */
    private static GlobalConfig globalConfig() {
        return new GlobalConfig()
                .setOutputDir(System.getProperty("user.dir") + "/" + MODULE + "/src/main/java")
                // 是否覆盖已有文件,默认false
                .setFileOverride(false)
                // 是否打开输出目录,默认true
                .setOpen(false)
                // 是否在xml中添加二级缓存配置,默认false
                // .setEnableCache(true)
                .setAuthor(AUTHOR)
                // 开启kotlin模式,默认false
                .setKotlin(false)
                // 开启swagger2模式,默认false
                .setSwagger2(true)
                // 开启ActiveRecord模式,默认false
                // .setActiveRecord(true)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                // 默认值未DateType.TIME_PACK 使用java8的时间类型
                .setDateType(DateType.TIME_PACK)
                // 可自定义,一般使用默认值即可
                // .setEntityName("%sEntity")
                // .setMapperName("%sMapper")
                // .setXmlName("%sMapper")
                .setServiceName("%sService")
                // .setServiceImplName("%ServiceImpl")
                // .setControllerName("%Controller")
                // 指定生成的主键的ID类型,默认为null,
                .setIdType(IdType.ASSIGN_ID);
    }

    /**
     * 注入配置
     */
    private static InjectionConfig injectionConfig() {
        return new InjectionConfig() {
            // 注入自定义Map对象,注意需要setMap放进去
            @Override
            public void initMap() {
            }
        }
                // 自定义返回配置Map对象,该对象可以传递到模板引擎通过 cfg.xxx引用
                .setMap(null)
                // 自定义输出文件,配置 FileOutConfig 指定模板文件,输出文件达到自定义文件生成目的
                .setFileOutConfigList(Collections.singletonList(fileOutConfig()))
                // 自定义判断是否创建文件,实现IFileCreate接口,该配置用于判断某个类是否需要覆盖创建,当然你可以自己实现差异算法merge文件
                .setFileCreate(null);
    }

    private static FileOutConfig fileOutConfig() {
        return new FileOutConfig(TEMPLATE_PATH) {

            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return System.getProperty("user.dir") + "/" + MODULE + MAPPER_XML + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        };
    }
}

