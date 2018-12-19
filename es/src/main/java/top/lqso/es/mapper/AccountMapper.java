package top.lqso.es.mapper;

import org.apache.ibatis.annotations.Select;
import top.lqso.es.model.AccountInfo;

import java.util.List;

public interface AccountMapper {
    @Select("select yhbh, yhmc, yddz from kh_ydkh where rownum < #{size}")
    List<AccountInfo> findSome(int size);
}
