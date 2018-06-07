package com.zhht.project.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

/**
 * redis服务类
* @moudle: RedisCacheService 
* @version:v1.0
* @Description: TODO
* @author: xubin
* @date: 2017年3月3日 下午6:23:54
*
 */
@Service
public class RedisCacheService {
	@Autowired
	protected RedisTemplate<String, Object> redisTemplate;

	/**
	 * 添加
	*
	* <p>Title: set</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:25:52</p>
	* @param key
	* @param value
	 */
	public void set(final String key, final Object value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@SuppressWarnings("unchecked")
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(key),
						((RedisSerializer<Object>) redisTemplate.getDefaultSerializer()).serialize(value));
				return null;
			}
		});
	}

	/**
	 * 添加
	*
	* <p>Title: set</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:26:09</p>
	* @param key
	* @param value
	* @param liveTime 单位秒
	 */
	public void set(final String key, final Object value, final long liveTime) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@SuppressWarnings("unchecked")
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(redisTemplate.getStringSerializer().serialize(key), liveTime,
						((RedisSerializer<Object>) redisTemplate.getDefaultSerializer()).serialize(value));
				return null;
			}
		});
	}

	/**
	 * 
	* <p>Title: setNX  </p>
	* Description: redis setNx操作
	* @author 王长亮 wangchangliang@zhihuihutong.com
	* @date 2017年6月23日 下午5:35:57  
	* @param key
	* @param value
	* @return
	 */
	public boolean setNX(final String key, final Object value) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@SuppressWarnings("unchecked")
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.setNX(redisTemplate.getStringSerializer().serialize(key),
						((RedisSerializer<Object>) redisTemplate.getDefaultSerializer()).serialize(value));
			}
		});
	}
	/**
	 * 
	* <p>Title: setNX  </p>
	* Description: redis setNx方法扩展，支持设置超时时间
	* @author 王长亮 wangchangliang@zhihuihutong.com
	* @date 2017年6月23日 下午5:49:16  
	* @param key
	* @param value
	* @param liveTime 超时时间 单位 秒
	* @return
	 */
    public boolean setNX(final String key, final Object value, final long liveTime) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@SuppressWarnings("unchecked")
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.multi();
				connection.setNX(redisTemplate.getStringSerializer().serialize(key),
						((RedisSerializer<Object>) redisTemplate.getDefaultSerializer()).serialize(value));
				connection.expire(redisTemplate.getStringSerializer().serialize(key), liveTime);
				List<Object> exec = connection.exec();
				return (Boolean) exec.get(0);
			}
		});
	}

	/**
	 * 根据key获取value
	*
	* <p>Title: get</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:26:55</p>
	* @param key
	* @return
	 */
	public <T> T get(final String key) {
		return redisTemplate.execute(new RedisCallback<T>() {
			@SuppressWarnings("unchecked")
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] value = connection.get(redisTemplate.getStringSerializer().serialize(key));
				if (value == null) {
					return null;
				}
				return (T) redisTemplate.getDefaultSerializer().deserialize(value);
			}
		});
	}
	
	/** 
	* <p>Title: getWithinSpecifiedTime  </p>
	* Description: 在指定时间内获取指定key值对应的value，超时返回null
	* @author 王长亮 wangchangliang@zhihuihutong.com
	* @date 2017年7月31日 下午12:04:48  
	* @param key 
	* @param timeout 超时时间 ，单位ms
	* @return
	*/
	public <T> T getWithinSpecifiedTime(final String key,final long timeout){
		return redisTemplate.execute(new RedisCallback<T>() {
			@SuppressWarnings("unchecked")
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] value = null;
				long start = System.currentTimeMillis();
				for(;;){
					long current = System.currentTimeMillis();
					value = connection.get(redisTemplate.getStringSerializer().serialize(key));
					if(value!=null || (current - start)>=timeout){
						break;
					}
				}
				if(value == null) {
					return null;
				}
				return (T) redisTemplate.getDefaultSerializer().deserialize(value);
			}
		});
	}

	/**
	 * 判断key是否存在
	*
	* <p>Title: exists</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:27:16</p>
	* @param key
	* @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(redisTemplate.getStringSerializer().serialize(key));
			}
		});
	}

	/**
	 * 
	*
	* <p>Title: get</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:27:44</p>
	* @param key
	* @param liveTime
	* @return
	 */
	public <T> T get(final String key, final long liveTime) {
		return redisTemplate.execute(new RedisCallback<T>() {
			@SuppressWarnings("unchecked")
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] bkey = redisTemplate.getStringSerializer().serialize(key);
				byte[] value = connection.get(bkey);
				if (value == null) {
					return null;
				}
				connection.expire(bkey, liveTime);
				return (T) redisTemplate.getDefaultSerializer().deserialize(value);
			}
		});
	}

	/**
	 * 删除
	*
	* <p>Title: del</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:27:54</p>
	* @param key
	* @return
	 */
	public long del(final String key) {
		return redisTemplate.execute(new RedisCallback<Long>() {

			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.del(redisTemplate.getStringSerializer().serialize(key));
			}
		});
	}

	/**
	 * 更新缓存
	*
	* <p>Title: update</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:28:13</p>
	* @param key
	* @param value
	* @param liveTime
	 */
	public void update(final String key, final Object value, final long liveTime) {
		this.set(key, value, liveTime);
	}

	/**
	 * 更新缓存
	*
	* <p>Title: update</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:28:37</p>
	* @param key
	* @param value
	 */
	public void update(final String key, final Object value) {
		this.set(key, value);
	}

	/**
	 * 
	*
	* <p>Title: expire</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:28:56</p>
	* @param key
	* @param liveTime
	* @return
	 */
	public boolean expire(final String key, final long liveTime) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.expire(redisTemplate.getStringSerializer().serialize(key), liveTime);
			}
		});
	}

	/**
	 * 
	*
	* <p>Title: hset</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:29:00</p>
	* @param key
	* @param field
	* @param value
	* @param liveTime
	 */
	public void hset(final String key, final String field, final Object value, final long liveTime) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@SuppressWarnings("unchecked")
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] bkey = redisTemplate.getStringSerializer().serialize(key);
				connection.hSet(bkey, redisTemplate.getStringSerializer().serialize(field),
						((RedisSerializer<Object>) redisTemplate.getDefaultSerializer()).serialize(value));
				if (liveTime != 0) {
					connection.expire(bkey, liveTime);
				}
				return null;
			}
		});
	}

	/**
	 * 
	*
	* <p>Title: hget</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:29:05</p>
	* @param key
	* @param field
	* @return
	 */
	public <T> T hget(final String key, final String field) {
		return redisTemplate.execute(new RedisCallback<T>() {
			@SuppressWarnings("unchecked")
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] value = connection.hGet(redisTemplate.getStringSerializer().serialize(key),
						redisTemplate.getStringSerializer().serialize(field));
				if (value == null) {
					return null;
				}
				return (T) redisTemplate.getDefaultSerializer().deserialize(value);
			}
		});
	}
	
	/**
	 * 
	*
	* <p>Title: hgetAll</p>
	* <p>author : xubin</p>
	* <p>date : 2017年8月11日 上午10:17:37</p>
	* @param key
	* @return Map<byte[],byte[]> 
	 */
	public Map<byte[],byte[]> hgetAll(final String key) {
        return redisTemplate.execute(new RedisCallback<Map<byte[],byte[]>>() {
            public Map<byte[],byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hGetAll(redisTemplate.getStringSerializer().serialize(key));
            }
        });
    }

	/**
	 * 
	*
	* <p>Title: hdel</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:29:09</p>
	* @param key
	* @param field
	* @return
	 */
	public long hdel(final String key, final String field) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hDel(redisTemplate.getStringSerializer().serialize(key),
						redisTemplate.getStringSerializer().serialize(field));
			}
		});
	}

	/**
	 * 
	*
	* <p>Title: hexists</p>
	* <p>author : xubin</p>
	* <p>date : 2017年3月3日 下午6:29:13</p>
	* @param key
	* @param field
	* @return
	 */
	public boolean hexists(final String key, final String field) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hExists(redisTemplate.getStringSerializer().serialize(key),
						redisTemplate.getStringSerializer().serialize(field));
			}
		});
	}
	
	/**
     * hash类型:返回 key 指定的哈希集中所有字段的名字
     * @param key
     * @return
     */
    public Set<byte[]> hkeys(final String key) {
        return redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.hKeys(redisTemplate.getStringSerializer().serialize(key));
            }
        });
    }
	
	/**
     * hash类型:可同时对key设置多个值，hset只能一次设置一个
     * @param key
     * @param map
     * @return
     */
    public Void hmset(final String key, final Map<byte[], byte[]> map) {
        return redisTemplate.execute(new RedisCallback<Void>() {
            public Void doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.hMSet(redisTemplate.getStringSerializer().serialize(key),map);
                return null;
            }
        });
    }
    /**
     * hash类型:返回 key 指定的哈希集中指定多个字段的值。
     * @param key
     * @param fields
     * @return
     */
    public List<byte[]> hmget(final String key, final byte[]... fields) {
        return redisTemplate.execute(new RedisCallback<List<byte[]>>() {
            public List<byte[]> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.hMGet(redisTemplate.getStringSerializer().serialize(key),fields);
            }
        });
    }

	
	/**
	 * 
	*
	* <p>Title: setEX</p>
	* <p>author : zhaowen</p>
	* <p>date : 2017年3月3日 下午6:29:13</p>
	* @param key
	* @param field
	* @return
	 */
	public boolean setEX(final String key, final Object value, final long liveTime) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@SuppressWarnings("unchecked")
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.multi();
				connection.setEx(redisTemplate.getStringSerializer().serialize(key), liveTime, 
						((RedisSerializer<Object>) redisTemplate.getDefaultSerializer()).serialize(value));
				List<Object> exec = connection.exec();
				return true;
			}
		});
	}
	
	
	/**
	 * 
	* <p>Title: keys  </p>
	* Description: TODO(模糊查询)
	* @author 赵文 zhaowen@zhihuihutong.com
	* @date 2017年7月27日 上午11:37:48  
	* @param pattern
	* @return
	 */
     public Set<byte[]> keys(final String pattern){
         return redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
             @Override
             public Set<byte[]> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                 return redisConnection.keys(redisTemplate.getStringSerializer().serialize(pattern));
             }
         });
     }
     
     /**
      * zset有序集合类型:
      * @param     key
      * @param offset    分数值是一个双精度的浮点型数字字符串
      * @param bytes2    value
      * @return
      */
     public Boolean zadd(final String key, final Double offset, final byte[] bytes2) {
         return redisTemplate.execute(new RedisCallback<Boolean>() {
             public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                 return redisConnection.zAdd(redisTemplate.getStringSerializer().serialize(key),offset,bytes2);
             }
         });
     }
     /**
      * zset有序集合类型:根据集合中指定的index返回成员列表
      * @param key
      * @param start
      * @param end
      * @return
      */
     public Set<byte[]> zrange(final String key, final Long start, final Long end) {
         return redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
             public Set<byte[]> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                 return redisConnection.zRange(redisTemplate.getStringSerializer().serialize(key),start,end);
             }
         });
     }
     /**
      * zset有序集合类型:
      * 从排序的集合中删除一个或多个成员
      * 返回值为从有序集合中删除的成员个数，不包括不存在的成员
      * @param key
      * @param bytes
      * @return
      */
     public Long zrem(final String key, final byte[]... bytes) {
         return redisTemplate.execute(new RedisCallback<Long>() {
             public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                 byte[] keys = redisTemplate.getStringSerializer().serialize(key);
                 return redisConnection.zRem(keys,bytes);
             }
         });
     }
     /**
      * zset有序集合类型:为有序集key的成员member的offset值加上增量increment
      * @param key    key
      * @param offset    增量increment
      * @param field    集合成员
      * @return  member成员的新offset值
      */
     public Double zincrby(final String key, final Double offset, final String field) {
         return redisTemplate.execute(new RedisCallback<Double>() {
             public Double doInRedis(RedisConnection redisConnection) throws DataAccessException {
                 return redisConnection.zIncrBy(
                         redisTemplate.getStringSerializer().serialize(key),
                         offset,
                         redisTemplate.getStringSerializer().serialize(field));
             }
         });
     }
     /**
      * zset有序集合类型:找到指定区间范围的数据进行返回
      * @param key
      * @param start
      * @param end
      * @return
      */
     public Set<byte[]> zrangebyscore(final String key, final Double start, final Double end) {
         return redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
             public Set<byte[]> doInRedis(RedisConnection redisConnection) throws DataAccessException {
                 return redisConnection.zRangeByScore(redisTemplate.getStringSerializer().serialize(key),start,end);
             }
         });
     }
     /**
      * zset有序集合类型:移除有序集key中，指定排名(rank)区间内的所有成员。
      * @param key
      * @param start
      * @param end
      * @return
      */
     public Long zremrangebyrank(final String key, final Double start, final Double end) {
         return redisTemplate.execute(new RedisCallback<Long>() {
             public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                 byte[] keys = redisTemplate.getStringSerializer().serialize(key);
                 return redisConnection.zRemRangeByScore(keys,start,end);
             }
         });
     }
     /**
      * zset有序集合类型:返回有序集key中，score值在min和max之间(默认包括score值等于min或max)的成员。
      * @param key
      * @param start
      * @param end
      * @return
      */
     public Long zcount(final String key, final Double start, final Double end) {
         return redisTemplate.execute(new RedisCallback<Long>() {
             @Override
             public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                 byte[] keys = redisTemplate.getStringSerializer().serialize(key);
                 return redisConnection.zCount(keys,start,end);
             }
         });
     }

}
