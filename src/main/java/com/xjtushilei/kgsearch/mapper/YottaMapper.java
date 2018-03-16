package com.xjtushilei.kgsearch.mapper;

import com.xjtushilei.kgsearch.model.AssembleFragment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface YottaMapper {

    @Select("SELECT * FROM assemble_fragment order by FragmentID")
    List<AssembleFragment> findAll();

    @Select("SELECT * FROM assemble_fragment where ClassName=#{className} order by FragmentID ")
    List<AssembleFragment> findAllByClassName(String className);

    @Select("SELECT count(FragmentID) FROM assemble_fragment")
    long count();

    @Select("SELECT count(FragmentID) FROM assemble_fragment where ClassName=#{className}")
    long countByClassName(String className);


}