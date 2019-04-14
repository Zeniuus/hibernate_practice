package kr.co.vcnc.swan.study.hibernate.practice.repository

import kr.co.vcnc.swan.study.hibernate.practice.model.Event
import org.springframework.data.repository.CrudRepository

interface EventRepository : CrudRepository<Event, Long>
