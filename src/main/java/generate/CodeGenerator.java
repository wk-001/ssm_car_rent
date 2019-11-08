package generate;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 代码生成器
 */
public class CodeGenerator {

	/**
	 * 数据库表名
	 */
	private static final String TABLE_NAME = "sys_user";

	/**
	 * 包名
	 */
	private static final String PACKAGE_NAME = "com.wk.sys";

	/**
	 * 代码生成者
	 */
	private static final String AUTHOR = "wk";

	/**
	 * 接口生成位置
	 */
	private static final String INTERFACE_PATH = "/src/main/resources/mapper/sys/";

	/**
	 * JDBC相关配置
	 */
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/car_rent?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC&rewriteBatchedStatements=true";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor(AUTHOR);//作者名称
		gc.setOpen(false);
		// gc.setSwagger2(true); 实体属性 Swagger2 注解
		gc.setFileOverride(true);	//第二次生成的文件覆盖第一次的
		gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML 生成ResultMap
		gc.setBaseColumnList(true);// XML columList 生成基本的SQL片段
		gc.setAuthor(AUTHOR);// 作者

		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		gc.setControllerName("%sController");
		gc.setServiceName("%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(URL);
		// dsc.setSchemaName("public");
		dsc.setDriverName(DRIVER);
		dsc.setUsername(USER_NAME);
		dsc.setPassword(PASSWORD);
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(PACKAGE_NAME);
//        pc.setModuleName("dao"); 自定义包名
//        pc.setModuleName("dao.Impl");
		pc.setMapper("dao");//dao
		pc.setService("service");//servcie
		pc.setController("controller");//controller
		pc.setEntity("pojo");

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};

		// 如果模板引擎是 freemarker
		String templatePath = "/templates/mapper.xml.ftl";
		// 如果模板引擎是 velocity
		// String templatePath = "/templates/mapper.xml.vm";

		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
               /* return projectPath + "/src/main/resources/mappers/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;*/
				//根据自己的位置修改
				return projectPath + INTERFACE_PATH +tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
		mpg.setPackageInfo(pc);
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();

		// 配置自定义输出模板
		//指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		// templateConfig.setEntity("templates/entity2.java");
		// templateConfig.setService();
		// templateConfig.setController();
		//此处设置为null，就不会再java下创建xml的文件夹了
		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);		//数据库表下划线驼峰命名
		strategy.setColumnNaming(NamingStrategy.underline_to_camel); //字段下划线驼峰命名
		//strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		// 公共父类
		//strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
		// 写于父类中的公共字段
		//strategy.setSuperEntityColumns("id");
		//表名
		strategy.setInclude(TABLE_NAME);
		strategy.setControllerMappingHyphenStyle(true);
		//根据你的表名来建对应的类名，如果你的表名没有什么下划线，比如test，那么你就可以取消这一步
//		strategy.setTablePrefix("t_");
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

}