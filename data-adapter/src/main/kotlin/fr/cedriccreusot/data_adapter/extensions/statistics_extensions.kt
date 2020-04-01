package fr.cedriccreusot.data_adapter.extensions

import fr.cedriccreusot.data_adapter.retrofit.model.Statistics

typealias DomainStatistics = fr.cedriccreusot.domain.model.Statistics

fun Statistics.toDomainStatistics() = DomainStatistics(
    download = this.downloads.total,
    likes = this.likes.total,
    views = this.views.total
)