package cn.itcast.elec.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.elec.dao.ICommonDao;
import cn.itcast.elec.util.GenericTypeUtils;

public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T> {

	Class entityClass = GenericTypeUtils.getGenericSuperClass(this.getClass());
	
	/**
	 * spring瀹瑰櫒涓畾涔�
	 * <bean id="commonDao" class="cn.itcast.elec.dao.impl.CommonDaoImpl">
		<property name="sessionFactory" ref="sessionFactory"></property>
	   </bean>
	 */
	@Resource(name="sessionFactory")
	public final void setSessionFactoryDi(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	/**淇濆瓨*/
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}
	
	/**鏇存柊*/
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}
	
	/**浣跨敤涓婚敭ID鏌ヨ瀵硅薄*/
	public T findObjectByID(Serializable id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}
	
	/**鍒犻櫎锛堜娇鐢�涓富閿甀D鍜屽涓富閿甀D鐨勬暟缁勶級*/
	public void deleteBojectByIDs(Serializable... ids) {
		if(ids!=null && ids.length>0){
			for(Serializable id:ids){
				Object entity = this.findObjectByID(id);
				this.getHibernateTemplate().delete(entity);
			}
		}
	}
	
	/**鍒犻櫎锛堝皢瀵硅薄灏佽鎴愰泦鍚堬紝浣跨敤闆嗗悎鍒犻櫎闆嗗悎涓瓨鏀剧殑鎵�湁瀵硅薄锛�/
	/**鐢ㄦ硶锛氬皢鏁版嵁鏌ヨ鑾峰彇灏佽鍒癓ist涓紝鍒犻櫎鍏ㄩ儴鐨刲ist锛屽厛鏌ヨ鍐嶅垹闄�/
	public void deleteObjectByCollection(List<T> list) {
		this.getHibernateTemplate().deleteAll(list);
	}
	
	/**鎸囧畾椤甸潰浼犻�鐨勬煡璇㈡潯浠讹紝鏌ヨ瀵瑰簲鐨勭粨鏋滈泦淇℃伅锛岃繑鍥濴ist<ElecText>锛屼笉鍒嗛〉*/
	/**
		SELECT * FROM elec_text o WHERE 1=1      #Dao灞�
		AND o.textName LIKE '%寮�'           #Service灞�
		AND o.textRemark LIKE '%寮�'         #Service灞�
		ORDER BY o.textDate ASC,o.textRemark DESC  #Service灞�
	 */
	public List<T> findCollectionByConditionNoPage(String condition,
			final Object[] params, Map<String, String> orderby) {
		String hql = "select o from "+entityClass.getSimpleName()+" o where 1=1 ";
		//瑙ｆ瀽map闆嗗悎锛岃幏鍙栨帓搴忕殑璇彞
		String orderbyhql = this.orderby(orderby);
		final String finalHql = hql + condition + orderbyhql;
		//鏂瑰紡涓�
		//List<T> list = this.getHibernateTemplate().find(finalHql, params);
		//鏂瑰紡浜岋紝浣跨敤hibernate妯℃澘鎻愪緵鐨勫洖璋冨嚱鏁帮紝鍥炶皟Session
		List<T> list = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(finalHql);
				if(params!=null && params.length>0){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				return query.list();
			}
		});
		return list;
	}

	//瑙ｆ瀽map闆嗗悎锛岃幏鍙栨帓搴忕殑璇彞锛孫RDER BY o.textDate ASC,o.textRemark DESC
	private String orderby(Map<String, String> orderby) {
		StringBuffer buffer = new StringBuffer("");
		if(orderby!=null && orderby.size()>0){
			buffer.append(" order by ");
			for(Map.Entry<String, String> map:orderby.entrySet()){
				buffer.append(map.getKey()+" "+map.getValue()+",");
			}
			//鍒犻櫎鏈�悗涓�釜閫楀彿
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}

	public void deleteObjectByCollection(List<T> list) {
		// TODO Auto-generated method stub
		
	}

}
