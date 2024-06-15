package com.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.report.entity.IpcReport;


@Repository
public interface IpcReportRepository extends JpaRepository<IpcReport, Long> {
}
